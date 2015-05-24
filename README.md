## techSupport
=========

Ukážková aplikácia k diplomovej práci: Bezpečnosť cloudov7ch PaaS

Ide o aplikáciu, ktorá reprezentuje systém technickej podpory, pre softvérové spoločnosti. Zostavenie aplikácie je možné pomocou Apache Maven.
S menšími úpravami konfiguračných súborov pre picketlink, je možné nasadiť aplikáciu na viaceré PaaS platformy. Konfiguračné súbory sa nachádzajú v zložke:

_techSupport/techSupport-web/src/webapp/WEB-INF/picketlink.xml_

Platformy, na ktoré je možné nasadiť aplikáciu :
-  Windows Azure
-  OpenShift

Postupy nasadenia na jednotlivé platformy :

### OpenShift

Po registrácii na OpenShift online, máme k dispozícii v rámci účtu zadarmo, možnosť vytvorenia 3 gearov, na ktorých môžeme spúšťať naše aplikácie.


Budeme potrebovať nainštalovať IDP v podobe KeyCloak serveru, na strane poskytovateľa.
Inštalácia KeyCloaky na OpenShift je popísaná na:

http://docs.jboss.org/keycloak/docs/1.1.0.Final/userguide/html/openshift.html

Po úspešnej inštalácií KeyCloaku a prvom prihlásení sa, pomocou mena/hesla :
admin/admin, ktoré však bude po prvotnom prihlásení zmenené, vytvoríme v rámci
KeyCloaku "realm" s názvom saml-demo.

V sekcii "Users" vytvoríme užívateľa s rolou "admin", ktorý bude v našej
aplikácii predstavovať administrátora. Ďalej môžeme vytvoriť užívateľov
s rolami "technician" pre technikov, prípadne užívateľské účty s rolou "user".

V rámci saml-demo realm vytvoríme v sekcii "Applications" novú aplikáciu
s menom, ktoré bude mať naša aplikácia na OpenShifte po nasadení. Zvolíme
z ponuky ako "client protocol" SAML a do sekcií

"Redirect URL" : https://${app-name}.rhcloud.com/*
"Default Redirect URL" : https://${app-name}.rhcloud.com
"Admin URL" : https://${app-name}.rhcloud.com

Pre nasadenie samotnej aplikácie na OpenShift, potrebujeme vytvoriť gear
s "diy" a "postgresql-9.2" cartridge:

rhc app create ${app-name} diy-0.1 postgresql-9.2 -l ${user-name}


Na našom stroji s vytvorí zložka ${app-name}, ktorá obsahuje git repozitár našej aplikácie. OpenShift používa na nasadzovanie aplikácií Git, pomocou ktorého nahráme na platformu naše zdrojové kódy a OpenShift sa následne postará o ich zostavenie, pomocou Apache Maven. Git repozitáre pre jednotlivé geary poskytuje pri ich vytvorení.
Pre komunikáciu s OpenShift budeme tiež potrebovať nástroj rhc, ktorý si musíme nainštalovať na vývojovom stroji.
Následne prekopírujeme do zložky "${app-name}/.openshift/action_hooks/" dva skripty
ktoré sa nachádzajú v git repozitári našej aplikácie.
*Ide o štartovací a vypínací skript :
*start
*stop

Nakonfigurujeme pgcrypto extension na postgresql:
* psql ${dbname}
* create extension pgcrypto;


ďalej prekopírujeme obsah git repozitára. Spravíme :

* git add -A
* git commit -m "${commit-message}"
* git push origin master

V príkazovom riadku môžeme pozorovať výstup zostavenia pomocou maven na strane
 OpenShiftu.
 Po chvíli je aplikácia zostavená a spustená na strane poskytovateľa. V prípade
 správnej konfigurácie je aplikácia dostupná na:

https://${meno_gearu}-${meno_účtu}.rhcloud.com


### Windows Azure

Pre využitie Windows Azure ako PaaS platformy, s využitím Worker Roles, sme použili vývojové prostredie Eclipse, s nainštalovanými zásuvnými modulmi, pre prácu s Windows Azure.

Pre nasadenie na Windows, použijeme vetvu winazure.

Vytvoríme v Azure portáli databázu, ktorú budeme v aplikácii používať. Údaje, pre komunikáciu s databázou budú musieť byť umiestnené do konfiguračného súboru techSupport-impl/src/main/resources/applicationContext.xml

Samotnému nasadeniu na windows azure predchádza konfigurácia WAAD, na ktorom musíme vytvoriť novú aplikáciu s menom, ktoré bude naša aplikácia využívať. http://${meno_aplikácie}.cloudapp.net/techSupport/

Budeme musieť upraviť manifest súbor pre aplikáciu, vzor tohoto manifestu sa nachádza spolu s aplikáciou vo vetve winazure, v zložke /folder

Následne upravíme v rámci aplikácie umiestnený súbor picketlink.xml, ktorému priradíme ako IDP endpoint nášho WAAD. Ako URL SP priradíme http://${meno_aplikácie}.cloudapp.net/techSupport/. Zostavíme lokálne aplikáciu, pomocou Apache Maven : maven clean install

Vytvoríme rovnako niekoľko ukážkových užívateľov, roly im budú priradené na základe manifestu, ktorý sme upravili v predchádzajúcich krokoch. Tieto roly budú používané picketlinkom, na určenie oprávnení v aplikácii.

Tomcat vo verzii 7.50 na ktorý budeme nasadzovať našu aplikáciu musí byť rovnako upravený. Musia mu byť pridané .jar súboru do lib zložky. Jar súbory sa rovnako nachádzajú v našom repozitári. Na otestovanie funkčnosti lokálne, môžeme využiť Windows Azure emulátor, na nasadenie na platformu Windows Azure, zvolíme možnosť publikácie na servery Windows Azure.

V eclipse s nainštalovaným azure SDK vytvoríme nový Azure Deployment Projekt. Tomu priradíme buď javu, ktorú máme v našom vývojomvom prostredí, naša aplikácia je však otestovaná a funkčná s poskytovanou Zulu Open JDK 8. Zvolíme ďalej ako behové prostredie náš upravený tomcat a pridáme ďalej zostavený .war súbor našej aplikácie, ktorý premenujeme na techSupport.war (prípadne zvolíme nami preferované meno).

Ďalej musíme nastaviť SSL offloading, pre zabezpečenie aplikácie pomocou SSL. Môžeme pre testovacie účely využiť self signed certifikáty, ktoré si vygenerujeme priamo v dialógu pre konfiguráciu SSL offloadingu.

Azure deployment projektu vytvoríme worker role inštanciu na Windows Azure. Ďalej mu priradíme diskový priestor a môžeme náš projekt nasadiť na cloud windows Azure.

Po nasadení, bude aplikácia dostupná na :

http://${meno_aplikácie}.cloudapp.net/techSupport/
