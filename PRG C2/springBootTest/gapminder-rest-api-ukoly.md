# REST API ve Spring Bootu – úkoly nad Gapminder CSV

CSV soubor `gapminderDataFiveYear.csv` obsahuje data o zemích světa v pětiletých intervalech. Každý řádek představuje jednu zemi v jednom konkrétním roce.

## Struktura CSV souboru

Soubor obsahuje tyto sloupce:

```text
country,year,pop,continent,lifeExp,gdpPercap
```

Význam sloupců:

| Sloupec | Význam |
|---|---|
| `country` | název země |
| `year` | rok záznamu |
| `pop` | počet obyvatel |
| `continent` | kontinent |
| `lifeExp` | očekávaná délka života |
| `gdpPercap` | HDP na obyvatele |

Data jsou dostupná pro roky od **1952** do **2007** vždy po pěti letech.

---

# Úkol 1 – Výpis zemí podle roku

## Zadání

Vytvořte REST API endpoint, který vrátí všechny záznamy z CSV souboru pro konkrétní rok.

## Endpoint

```http
GET /api/gapminder/year/{year}?continent={continent}
```

## Příklad volání

```http
GET /api/gapminder/year/2007?continent=Europe
```

API vrátí seznam všech zemí, které mají v CSV souboru záznam pro rok `2007`.

Přidejte query parametr `continent`, pomocí kterého půjde výsledek omezit pouze na jeden kontinent. Query parametr je volitelný (uživatel API ho nemusí zadávat).

## Příklad odpovědi

```json
[
  {
    "country": "Afghanistan",
    "year": 2007,
    "pop": 31889923,
    "continent": "Asia",
    "lifeExp": 43.828,
    "gdpPercap": 974.5803384
  },
  {
    "country": "Albania",
    "year": 2007,
    "pop": 3600523,
    "continent": "Europe",
    "lifeExp": 76.423,
    "gdpPercap": 5937.029526
  }
]
```

---

# Úkol 2 – Statistiky kontinentu v určitém roce

## Zadání

Vytvořte endpoint, který pro zadaný kontinent a rok vrátí souhrnné statistiky.

## Endpoint

```http
GET /api/gapminder/continents/{continent}/stats?year={year}
```

## Příklad volání

```http
GET /api/gapminder/continents/Europe/stats?year=2007
```

API najde všechny země daného kontinentu v daném roce a spočítá nad nimi statistiky.

## Výstup musí obsahovat

- název kontinentu,
- rok,
- počet zemí,
- celkovou populaci,
- průměrnou délku života,
- průměrné HDP na obyvatele,
- zemi s nejvyšší délkou života,
- zemi s nejnižší délkou života.

## Příklad odpovědi

```json
{
  "continent": "Europe",
  "year": 2007,
  "countryCount": 30,
  "totalPopulation": 586098529,
  "averageLifeExp": 77.6486,
  "averageGdpPercap": 25054.4816,
  "highestLifeExpCountry": "Iceland",
  "lowestLifeExpCountry": "Turkey"
}
```

## Požadavky

Aplikace musí:

- filtrovat data podle kontinentu,
- filtrovat data podle roku,
- spočítat počet zemí,
- spočítat celkovou populaci,
- spočítat průměrnou délku života,
- spočítat průměrné HDP na obyvatele,
- najít zemi s nejvyšší hodnotou `lifeExp`,
- najít zemi s nejnižší hodnotou `lifeExp`,
- vrátit jeden souhrnný objekt jako JSON.

---

# Úkol 3 – Největší zlepšení délky života

## Zadání

Vytvořte endpoint, který najde země s největším zlepšením očekávané délky života mezi dvěma roky.

## Endpoint

```http
GET /api/gapminder/life-expectancy/improvement?from={fromYear}&to={toYear}&limit={limit}
```

## Příklad volání

```http
GET /api/gapminder/life-expectancy/improvement?from=1952&to=2007&limit=5
```

API porovná hodnotu `lifeExp` pro každou zemi v roce `from` a v roce `to`.

Pro každou zemi spočítá rozdíl:

```text
zlepšení = lifeExp v roce to - lifeExp v roce from
```

Výsledky seřadí od největšího zlepšení po nejmenší a vrátí pouze prvních `limit` zemí.

## Příklad odpovědi

```json
[
  {
    "country": "Oman",
    "continent": "Asia",
    "fromYear": 1952,
    "toYear": 2007,
    "lifeExpFrom": 37.578,
    "lifeExpTo": 75.64,
    "improvement": 38.062
  },
  {
    "country": "Vietnam",
    "continent": "Asia",
    "fromYear": 1952,
    "toYear": 2007,
    "lifeExpFrom": 40.412,
    "lifeExpTo": 74.249,
    "improvement": 33.837
  }
]
```

## Požadavky

Aplikace musí:

- najít pro každou zemi záznam v počátečním roce,
- najít pro stejnou zemi záznam v koncovém roce,
- spočítat rozdíl očekávané délky života,
- seřadit výsledky sestupně podle zlepšení,
- omezit počet výsledků pomocí parametru `limit`,
- vrátit výsledek jako JSON.

