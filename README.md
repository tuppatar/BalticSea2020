# BalticSea2020

Peli on laajennettu versio perinteisestä laivanupotuksesta. Peliin on tarkoitus lisätä laivojen lisäksi satama sekä saarille siiviliasumuksia, joihin vastustaja EI saa osua.

Uutta viikolla 5:
Tein maaston sellaiseksi että ranta olisi elävämmän näköinen. Huomasin, että tästä pystyi tietysti päättelemään viereisten palojen sisältöä. Olisin voinut muuttaa tekoälyä sellaiseksi että tietokone ottaa tämän huomioon, mutta korjasinkin asian niin että pelaajan näkemä maasto tarkentuu sitä mukaa kun viereisiä ruutuja on ammuttu. Eli näin ollen "älyn suhteen" pelaaja ja kone ovat taas samalla tasolla.
Peliin on lisätty yhden ruudun kokoinen sukellusvene, jonka toiminnallisuus on sama kuin laivoilla. Lisäksi peliin on lisätty taloja (3), jotka ovat siviiliasumuksia ja niihin osuessa rangaistukseksi vastustaja saa kolme peräkkäistä vuoroa. Nämä on nyt toteutettu.
Koska kurssi on jo näin pitkällä, en tiedä teenkö kahta suunnittelemaani toiminnallisuutta (telakka, ja toisena pelaajalle mahdollisuus valita laivapaikat itse pelin alussa) sillä loppuaika menee varmaan, kun peliin tekee kunnon käyttöliittymän valikkoineen ja info-teksteineen joita tulostuu pelin aikana jne.
Olen siistinyt koodia nyt palautteen perusteella huomattavasti. Metodeja on pilkottu ja sisäkkäisiä iffejä ei ole enää liikaa. Muutama luokka on vielä karvan verran yli 200 riviä, mutta näistä Peli-luokka tulee vielä muuttumaan (eli varmaan jakautumaan osiin). Vaikka TekoAlyLaivalle on 210 riviä, sitä ei ehkä kannata pilkkoa, saako tästä palautetta??

Dokumentaatio:

[aiheen kuvaus](dokumentaatio/aiheenKuvausJaRakenne.md)

[tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[pit-raportti](https://htmlpreview.github.io/?https://github.com/tuppatar/BalticSea2020/blob/master/dokumentaatio/pit-raportti/index.html)

[checkstyle-raportti](https://htmlpreview.github.io/?https://github.com/tuppatar/BalticSea2020/blob/master/dokumentaatio/checkstyle-raportti/checkstyle.html)
