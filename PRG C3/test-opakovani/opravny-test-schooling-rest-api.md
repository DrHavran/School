# Opravný test – REST API nad CSV se školní docházkou

CSV soubor `mean-years-of-schooling-long-run.csv` obsahuje data o průměrném počtu let školní docházky v různých zemích světa. Každý řádek představuje jednu zemi v jednom konkrétním roce.

## Struktura CSV souboru

Soubor obsahuje tyto sloupce:

```text
Entity,Code,Year,Average years of schooling
```

Význam sloupců:

| Sloupec | Význam |
|---|---|
| `Entity` | název země |
| `Code` | třípísmenný kód země |
| `Year` | rok záznamu |
| `Average years of schooling` | průměrný počet let školní docházky |

Data obsahují záznamy pro více zemí a různé roky.

---

# Úkol 1 – Nejvzdělanější země v daném roce podle školní docházky

## Zadání

Vytvořte REST API endpoint, který pro zadaný rok vrátí země s nejvyšším průměrným počtem let školní docházky.

Výsledek bude možné volitelně omezit pomocí minimální hodnoty školní docházky a maximálního počtu vrácených zemí.

API zároveň pro každou zemi spočítá, o kolik let je její hodnota vyšší nebo nižší než průměr všech zemí v daném roce.

## Endpoint

```http
GET /api/schooling/top-countries?year={year}&min={minSchoolingYears}&limit={limit}
```

## Příklad volání

```http
GET /api/schooling/top-countries?year=2020&min=10&limit=5
```

API najde všechny záznamy pro rok `2020`, spočítá průměrnou hodnotu školní docházky pro tento rok, vyfiltruje země s hodnotou alespoň `10`, seřadí je od nejvyšší hodnoty školní docházky a vrátí maximálně `5` zemí.

Query parametry `min` a `limit` jsou volitelné.

Pokud není zadán parametr `min`, API nefiltruje podle minimální hodnoty školní docházky.

Pokud není zadán parametr `limit`, API vrátí všechny odpovídající záznamy.

## Výstup musí pro každou zemi obsahovat

- název země,
- kód země,
- rok,
- průměrný počet let školní docházky,
- rozdíl oproti průměru všech zemí v daném roce.

## Příklad odpovědi

```json
[
  {
    "country": "Germany",
    "code": "DEU",
    "year": 2020,
    "averageYearsOfSchooling": 14.1,
    "differenceFromYearAverage": 5.18
  },
  {
    "country": "Czechia",
    "code": "CZE",
    "year": 2020,
    "averageYearsOfSchooling": 13.1,
    "differenceFromYearAverage": 4.18
  }
]
```

## Požadavky

Aplikace musí:

- načíst data z CSV souboru,
- filtrovat data podle roku,
- spočítat průměrnou hodnotu školní docházky pro všechny země v daném roce,
- volitelně filtrovat data podle minimální hodnoty školní docházky,
- seřadit výsledky sestupně podle průměrného počtu let školní docházky,
- volitelně omezit počet výsledků podle parametru `limit`,
- pro každou zemi spočítat rozdíl oproti průměru daného roku,
- vrátit seznam odpovídajících záznamů jako JSON.

## Výpočet rozdílu oproti průměru

```text
differenceFromYearAverage = averageYearsOfSchooling - průměrná hodnota školní docházky v daném roce
```

---

# Úkol 2 – Statistika školní docházky pro konkrétní rok

## Zadání

Vytvořte endpoint, který pro zadaný rok vrátí souhrnné statistiky o školní docházce.

## Endpoint

```http
GET /api/schooling/stats?year={year}
```

## Příklad volání

```http
GET /api/schooling/stats?year=2020
```

API najde všechny záznamy pro daný rok a spočítá základní statistiky.

## Výstup musí obsahovat

- rok,
- počet zemí,
- průměrný počet let školní docházky,
- nejvyšší hodnotu školní docházky,
- nejnižší hodnotu školní docházky,
- zemi s nejvyšší hodnotou,
- zemi s nejnižší hodnotou.

## Příklad odpovědi

```json
{
  "year": 2020,
  "countryCount": 145,
  "averageSchoolingYears": 8.92,
  "highestSchoolingYears": 14.3,
  "lowestSchoolingYears": 1.8,
  "highestSchoolingCountry": "Germany",
  "lowestSchoolingCountry": "Niger"
}
```

## Požadavky

Aplikace musí:

- filtrovat data podle roku,
- spočítat počet zemí v daném roce,
- spočítat průměrnou hodnotu školní docházky,
- najít nejvyšší hodnotu školní docházky,
- najít nejnižší hodnotu školní docházky,
- najít zemi s nejvyšší hodnotou,
- najít zemi s nejnižší hodnotou,
- vrátit jeden souhrnný objekt jako JSON.

---

# Úkol 3 – Vývoj školní docházky ve vybrané zemi

## Zadání

Vytvořte endpoint, který pro vybranou zemi porovná průměrný počet let školní docházky mezi dvěma roky.

## Endpoint

```http
GET /api/schooling/countries/{country}/change?from={fromYear}&to={toYear}
```

## Příklad volání

```http
GET /api/schooling/countries/Czechia/change?from=1950&to=2020
```

API najde záznam vybrané země v počátečním i koncovém roce a spočítá změnu průměrného počtu let školní docházky.

## Výstup musí obsahovat

- název země,
- kód země,
- počáteční rok,
- koncový rok,
- hodnotu školní docházky v počátečním roce,
- hodnotu školní docházky v koncovém roce,
- absolutní změnu,
- procentuální změnu.

## Příklad odpovědi

```json
{
  "country": "Czechia",
  "code": "CZE",
  "fromYear": 1950,
  "toYear": 2020,
  "schoolingFrom": 7.8,
  "schoolingTo": 13.1,
  "schoolingChange": 5.3,
  "schoolingChangePercent": 67.95
}
```

## Výpočet procentuální změny

```text
procentuální změna = (schoolingTo - schoolingFrom) / schoolingFrom * 100
```

## Požadavky

Aplikace musí:

- najít záznam vybrané země v počátečním roce,
- najít záznam stejné země v koncovém roce,
- spočítat absolutní změnu školní docházky,
- spočítat procentuální změnu školní docházky,
- vrátit jeden souhrnný objekt jako JSON.

---

# Doporučení k názvům vlastností v JSON

Protože názvy sloupců v CSV obsahují mezery, je vhodné je v API převést na praktičtější názvy:

| Sloupec v CSV | Název v JSON |
|---|---|
| `Entity` | `country` |
| `Code` | `code` |
| `Year` | `year` |
| `Average years of schooling` | `averageYearsOfSchooling` |
