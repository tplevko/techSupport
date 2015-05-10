## techSupport
=========

Ukážková aplikácia k diplomovej práci: Bezpečnosť cloudov7ch PaaS

Ide o aplikáciu, ktorá reprezentuje systém technickej podpory, pre softvérové spoločnosti. Zostavenie aplikácie je možné pomocou Apache Maven.
S menšími úpravami konfiguračných súborov pre picketlink, je možné nasadiť aplikáciu na viaceré PaaS platformy. Konfiguračné súbory sa nachádzajú v zložke:

_techSupport/techSupport-web/src/webapp/WEB-INF/picketlink.xml_

Platformy, na ktoré je možné nasadiť aplikáciu :
- item Windows Azure
- item OpenShift

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
Ide o štartovací a vypínací skript :
start
stop

Nakonfigurujeme pgcrypto extension na postgresql:
psql ${dbname}
create extension pgcrypto;


ďalej prekopírujeme obsah git repozitára. Spravíme :

git add -A
git commit -m "${commit-message}"
git push origin master

V príkazovom riadku môžeme pozorovať výstup zostavenia pomocou maven na strane
 OpenShiftu.
 Po chvíli je aplikácia zostavená a spustená na strane poskytovateľa. V prípade
 správnej konfigurácie je aplikácia dostupná na:

https://${meno_gearu}-${meno_účtu}.rhcloud.com


### Windows Azure

Pre využitie Windows Azure ako PaaS platformy, s využitím Worker Roles, sme použili vývojové prostredie Eclipse, s nainštalovanými zásuvnými modulmi, pre prácu s Windows Azure.

Pre nasadenie na Windows, sme použili vetvu master, s predkonfigurovanou databázou, ktorá bude nasadená iba v pamäti. Nejde teda o produkčnú databázu a v prípade záujmu o produkčné nasadenie, by mala byť aplikácia dodatočne upravená.

Microsoft rovnako v základnej inštalácii aplikácie, neposkytuje šifrovanie pomocou SSL, existuje však rovnako možnosť, ďalšej konfigurácie aplikácie, aby SSL používala.

Po nasadení, bude aplikácia dostupná na :

http://${meno_aplikácie}.cloudapp.net/techSupport-web/

1. item zmeníme vetvu na master
2. item projekt otvoríme pomocou prostredia eclipse s nainštalovanými zásuvnými modulmi
2. item nastavíme konfiguračné súbory
3. item zostavíme lokálne aplikáciu, pomocou Apache Maven : maven clean install
4. item vytvoríme "Azure Deployment Projekt", vo vývojovom prostredí, nastavíme mu použitie nášho projektu, meno projektu, použitie Javy v prostredí na Windows Azure, Tomcat Server, ako server na nasadenie aplikácie.
5. item Na otestovanie funkčnosti lokálne, môžeme využiť Windows Azure emulátor, na nasadenie na platformu Windows Azure, zvolíme možnosť publikácie na servery Windows Azure.
