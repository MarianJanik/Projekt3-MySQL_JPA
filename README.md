### Krátké info k projektu:
- projekt jsem navrhl tak, abych otestoval své znalosti při programování se standardem JPA,
- protože jsem programoval před tímto projektem 3 předchozí úkoly, projekt byl pro mne náročným opakováním,
- k náročnějším částem patřila část C - statistika.


# 3. projekt
má 3 části A, B, C:

A/ Převedení vybraných dat (JSON) z URL adresy do databáze (MySQL).

B/ Vytvoření rozhraní (REST API) s daty z databáze podle požadavků.

C/ Vypočet základních statistických údajů z databáze.

Nakonec vše bude řízeno programem Postman.

Pro práci s databází bude použit standard Java Persistence API (JPA).

Do databáze bude místo hodnoty "false" zapsaná hodnota 0.

---


## A/ Program uloží vybraná data z https://euvatrates.com/rates.json do databáze.

(MainControllerA, složka json_mysql)

Postupně:

1. Zavolat API pomocí HTTP
2. Načíst JSON soubor
3. Naparsovat JSON soubor do objektu
4. Uložit data do MySQL databáze VAT
5. To vše metodou reset (REST API, Postman), která kdykoliv vymaže stávající databázi a obnoví v ní data


## B/ Program vytvoří REST API, které bude pracovat s daty ve vytvořené databázi.

(MainControllerBSelect, MainControllerBUpdate)

Úkoly pro Select, Delete, Update, Insert:

1. Výpis všech údajů
2. Výpis na základě zkratky státu
3. Výpis na základě názvu státu
4. Výpis na základě hraniční hodnoty - minima (maxima)
5. Výpis na základě intervalu (od-do)
6. Smazání na základě zkratky státu
7. Smazání na základě názvu státu
8. Zapsání nového údaje
9. Úprava údaje

Pro bod 4. a 5. program bude vycházet ze základní sazby DPH.

## C/ Program vypočte a odešle vybrané statistické údaje

(MainControllerC)

Statistické úkoly:

1. Počet všech záznamů v databázi
2. Počet záznamu nad minimum
3. Najděte státy s x nejvyššími hodnotami (x bude zadané číslo - zadaný počet hodnot, např. 4)
4. Najděte státy ve vymezeném intervalu a zjistěte jejich počet (počet, výpis názvů)
5. Průměrnou hodnotu 3 sazeb v databází

Pro bod 2., 3., 4. program bude vycházet ze základní sazby DPH,

pro bod 5. budou 3 sazby - základní sazba DPH, snížená sazba DPH a speciální sazba "parking".

---

Projekt 3 vychází z projektu 2, který namapoval JSON z zadané webové adresy
a pracoval přímo s daty z JSONu.
