### Krátké info k projektu:
- projekt jsem navrhl tak, abych otestoval své znalosti při programování se standardem JPA,
- protože jsem programoval před tímto projektem 3 předchozí úkoly, projekt byl pro mne náročným opakováním,
- k náročnějším částem patřila část C - statistika.


# 3. projekt
má 3 části A,B,C:

A/ Převedení vybraných dat (JSON) z URL adresy do databáze (MySQL).

B/ Vytvoření rozhraní (REST API) s daty z databáze podle požadavků.

C/ Vypočet základních statistických údajů z databáze.

Vše bude řízeno programem Postman.

Pro práci s databází bude použit standard Java Persistence API (JPA).
---


## A/ Program uloží vybraná data z https://euvatrates.com/rates.json do databáze.
### (MainControllerA, složka json_mysql)

Postupně:

1. Zavolat API pomocí HTTP
2. Načíst JSON soubor
3. Naparsovat JSON soubor do objektu
4. Uložit data do MySQL databáze VAT
5. To vše metodou reset, která kdykoliv vymaže stávající databázi a obnoví data


## B/ Program vytvoří REST API, které bude pracovat s vytvořenou databází
### (MainControllerB)

Úkoly pro REST API:

1. Výpis všech údajů
2. Výpis na základě pk (zkratky)
3. Výpis na základě názvu státu
4. Výpis na základě minima (maxima) 1 hodnoty
5. Výpis na základě intervalu (od-do)
6. Smazání na základě pk (zkratky)
7. Smazání na základě názvu státu
8. Zapsání nového údaje
9. Úprava údaje


## C/ Program vypočte a odešle  některé údaje
### (MainControllerC)

Výpočetní úkoly:

1. Počet všech záznamů v databázi,
2. Počet záznamu nad minimum,
3. Najděte státy s x nejvyššími hodnotami (x bude zadaný parametr, např. 4)
4. Najděte státy ve vymezeném intervalu (počet, výpis názvů),
5. Průměrnou hodnotu 3 sazeb v databází
---

Projekt 3 vychází z projektu 2, který namapoval JSON z zadané webové adresy
a pracoval 