Aihe: Perinteisestä laivanupotuspelistä laajennettu versio. Pelissä jossa simuloidaan Itämeren ennustettuja tapahtumia vuonna 2020. Normaaliin upotuspeliin verrattuna pelialue on neljä kertaa suurempi, 20 x 20 palaa. Meren lisäksi kussakin maastossa on maapaloja järkeväksi saaristoksi aseteltuna. Saarilla on siviiliasumuksia, joihin ei saa osua. Jos osuu, vastustaja saa kerralla useamman pelivuoron.

Pelissä on aloitusvalikko, jossa voi valita pelattavan valtion tai sotilasliiton sekä pelin parametreja: Laivojen, talojen sekä maaston määrät sekä saariston "rikkonaisuuden" asteen. Pelissä on myös musiikki ja äänet, jotka saa valikossa päälle.

Käyttäjät: pelaaja vastaan tietokone. Mahdollisuus laajentaa muotoon pelaaja vastaan pelaaja, mutta pelin luonteen vuoksi tätä toteutusta ei kannata tehdä samalla tietokoneella pelattavaksi. Tietokonevastustajan tekoäly välttelee alueita, joiden vieressä on paljastunut maata, koska lähistöllä on oletettavasti lisää maata. Se myös välttelee ammuttujen laivojen vierustaa, koska peli ei arvo laivoja vierekkäin tai "pääty reunaa" vasten. Samoin tekoäly osaa pommittaa lähelle laivapaloja, joihin on jo osuttu.

Luokkakaavio:

![Luokkakaavio](kaavio.png)

Peli luo molemmille pelaajille maaston. Maasto ei ole erillinen luokka, vaan kaksiulotteinen int-taulukko, mutta sen luomiseen on MaastonLuoja-luokka. Maastossa on eri arvo sen mukaan, onko maastopala vettä, maata, laivaa vai taloa tai jotain näistä ammuttuna. Erikseen luodaan PiirtotaulukonLuoja-luokalla piirtotaulukko, joka kertoo millainen pala ruudulle piirretään (esimerkiksi maapaloja, joilla on sama toiminta Maasto-taulukossa, on rantojen takia lukemattomia erilaisia piirtoversioita.) Laivojen sijoittelu jo luotuun maastoon on monimutkaisempaa, ja tässä käytetään omaa LaivanLuoja-luokkaa.

Pelaajan vuoron tapahtumat riippuu kursorin asemasta. Toiminto tehdään sen mukaan, mitä ruudun takaa paljastuu.

Tietokonevastustaja ampuu aluksi satunnaisesti. Jos ruudusta paljastuu laivaa, kone ampuu ensin lähistölle. Kun laivan suunta on pääteltävissä, kone ampuu sen mukaan. Kun laivan toinen pääty osoittautuu loppuneeksi, kone tietää mihin ampua. Sen lisäksi kone osaa vältellä alueita, joissa on paljon paljastunutta maastoa lähistöllä, sekä ammuttujen laivojen vierustoja. Tekoäly-luokka tekee ensin päättelyt, ja sen mukaan käytetään TekoälyLaivalle- tai TekoälyMuualle-luokkaa.

Pelin runkona on PeliRunko-luokka, joka luodaan kerran. Se tekee Menu-Luokan olion aloitusvalikoksi. Valikossa muutettavat parametrit menevät PeliMuuttujat-luokan olioon, josta ne ovat Pelin käytettävissä. Kun uusi peli aloitetaan, luodaan uusi Peli-olio. Pelin päättyessä palataan jo luotuun Menuun.

Pelin alussa luotava Kayttis-olio luo Grafiikka- ja NappaimistonKuuntelija-luokat, jotka toimivat taustalla. Grafiikka luo alussa KuvanLataaja-luokan, joka lataa kuvat muistiin. NappaimistonKuuntelija-luokalla on apuna NappainTapahtuma-olio, joka toimii kuuntelijan ja PeliRungon välillä tulkkina. PeliRunko luo myös Soittaja-olion. Se käyttää Aanet-oliota äänien soittamiseen.

Sekvenssikaavio uuden maaston luonnista:

![Sekvenssikaavio 1](sekve_1.png)

Sekvenssikaavio tekoälyn käytöstä:

![Sekvenssikaavio 2](sekve_2.jpg)

