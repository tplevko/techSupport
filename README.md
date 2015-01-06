## techSupport
=========

Ukážková aplikácia k diplomovej práci: Bezpečnosť cloudu

Ide o aplikáciu, ktorá reprezentuje systém technickej podpory, pre softvérové spoločnosti. Zostavenie aplikácie je možné pomocou Apache Maven.
S menšími úpravami konfiguračných súborov, je možné nasadiť aplikáciu na viaceré PaaS platformy. Konfiguračné súbory sa nachádzajú v zložke:

_techSupport/techSupport-web/src/main/resources/_

Ide o konfiguračné súbory:

_admin.properties_ a _config.properties_

Heslo pre administrátorský účet by sa malo zmeniť a po nasadení na platformy, by administrátor mal pridať nejaké produkty pre podporu a zákazníci následne môžu pridávať svoje požiadavky do systému.

Platformy, na ktoré je možné nasadiť aplikáciu :
- item Windows Azure
- item OpenShift
- item Pivotal Web Services
- item Heroku

Postupy nasadenia na jednotlivé platformy :

### OpenShift

Po registrácii na OpenShift online, máme k dispozícii v rámci účtu zadarmo, možnosť vytvorenia 3 gearov, na ktorých môžeme spúšťať naše aplikácie.

Pre našu aplikáciu vytvoríme gear, s EWS (EWS je produktizovaný Tomcat kontajner, poskytovaný spoločnosťou Red Hat) a PostgreSQL. Aplikácia po nasadení dostupná na URL (túto poskytneme konfiguračnému súboru _config.properties_): 

https://${meno_gearu}-${meno_účtu}.rhcloud.com

OpenShift používa na nasadzovanie aplikácií Git, pomocou ktorého nahráme na platformu naše zdrojové kódy a OpenShift sa následne postará o ich zostavenie, pomocou Apache Maven. Git repozitáre pre jednotlivé geary poskytuje pri ich vytvorení. 
Pre komunikáciu s OpenShift budeme tiež potrebovať nástroj rhc, ktorý si musíme nainštalovať na vývojovom stroji. 

V prípade, že vyvýjame na stroji s operačným systémom Linux, postup nasadenia aplikácie vyzerá nasledovne:

1. item rhc app create ${meno_aplikácie} tomcat-7 postgresql-9.2
2. item zmažeme ukážkovú aplikáciu vo vytvorenom git repozitári, pomocou rm -rf *
3. item  odstránili sme ukážkovú aplikáciu, musíme zmeny zaznamenať, pomocou: _git add_ a _git commit_ príkazov
4. item skopírujeme našu aplikáciu, z vetvy OpenShift : cp -rf ${cesta_ku_git_repozitáru}/techSupport/* ${OpenShift_git_repozitár} 
6. item povolenie automatického spustenia - touch .openshift/markers/hot_deploy
7. item commitneme zmeny : git add -A a následne commit 
8. item git push origin master - pridanie zmien do repozitára na OpenShifte

Na OpenShifte sa všetko následne zostaví pomocou Apache Maven zostavený .war súbor sa nasadí na poskytnutý Tomcat.
V prípade, že sme nakonfigurovali aplikáciu, pred jej nasadením na OpenShift, mala by fungovať podľa očakávaní.      

### Heroku

Heroku rovnako ako OpenShift používa Git a vlastný nástroj pre príkazový riadok, pre manipuláciu s platformou. Pre nasadenie na je teda prvým krokom registrácia na Heroku a stiahnutie spomínaného nástroja.

Čo sa týka konfigurácie samotnej aplikácie, je nutné pre štartovanie Tomcatu, použiť \uv{Tomcat runner}, čo je maven profil, štartujúci Tomcat, po zostavení aplikácie. Ten je už však pridaný v heroku vetve. PostgreSQL je poskytovaný ako jedna zo služieb, ktoré máme k dispozícii i v prípade použitia účtu zadarmo.

Podobe ako v prípade OpenShiftu, pre heroku dyno, ktoré si vytvoríme v rámci nášho účtu, sa vytvorí git repozitár. Ten si naklonujeme na náš vývojový stroj a môžeme do tohoto repozitáru skopírovať našu nakonfigurovanú aplikáciu.

K aplikácií môžeme v~prípade, že všetko prebehlo podľa očakávaní, pristupovať na adrese (túto poskytneme konfiguračnému súboru _config.properties_):

https://${meno_aplikácie}.herokuapp.com}

Detailný postup krokov pri nasadzovaní aplikácie na Heroku:
1. item naklonujeme heroku git repozitár: heroku git:clone -a ${meno_aplikácie}
2. item skopírujeme našu aplikáciu, z vetvy heroku : cp -rf ${cesta_ku_git_repozitáru}/techSupport/* ${heroku_git_repozitár} 
3. item upravenie konfiguracii + git commit...
4. item commitneme zmeny : git add -A a následne commit 
5. item git push heroku

Aplikácia sa rovnako zostaví pomocou Apache Maven, na strane Heroku a následne sa spustí, pomocou nami pridaného tomcat-runner-u v konfigurácii

### Pivotal Web Services

Musíme sa zaregistrovať rovnako aj u Pivotal Web Services, a využijeme tiež nástroj pre príkazový riadok, ktorým je v prípade Pivotal Web Services cf.
Vytvoríme si pomocou webového rozhrania run.pivotal.io, aplikáciu a pridáme jej poskytovanú databázu ElephantSQL, ktorá je založené na PostgreSQL.

Pivotal WS na rozdiel od vyššie uvedených PaaS neposkytuje vlastné Git repozitáre, využijeme teda tento, priložený repozitár.

Pomocou nástroja _cf_ sa prihlásime k nášmu Pivotal WS účtu, a nasadíme našu aplikáciu v nasledujúcich krokoch:

Po nasadení, bude naša aplikácia dostupná na adrese (pridáme do súboru _config.properties_):

https://${meno_aplikácie}.cfapps.io

1. item zmeníme vetvu na pivotal
2. item nakonfigurujeme konfiguračné súbory
3. item zostavíme lokálne aplikáciu, pomocou Apache Maven : maven clean install
4. item prevedieme nasadenie aplikácie, pomocou príkazu: cf push ${meno_aplikácie} -p ${git_repozitár}/techSupport-web/target/techSupport-web-1.0-SNAPSHOT.war -b https://github.com/cloudfoundry/java-buildpack.git
5. item aplikácia by po krátkej dobe mala byť dostupná na vyššie uvedenej adrese

Pivotal Web Services zostavuje aplikáciu na strane používateľa, na server sa nasadzuje iba vygenerovaný .war súbor.

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



