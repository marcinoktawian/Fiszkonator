directory = "./elpuerto/"
file = "miejsca.txt"
filename = directory + file

file1 = open(filename, "r")

spanish = False
polish = False
spanishText = ""
polishText = ""

idKategorii = "7, "
idZestawu = "2, '"
poziom = "', 'A2.1');"
record_index = 124

insert = (
    "INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES ("
    + idKategorii
    + idZestawu
)

all_inserts = []
stats_sql = "INSERT INTO Statystyki VALUES("
# ,0.0,0.0,1);"
all_stats = []
for line in file1.readlines():
    if not spanish:
        spanishText = line.strip()
        spanish = True
        continue
    if not polish:
        polishText = line.strip()
        polish = True
        continue

    if spanish and polish:
        # print(polishText + "-"+ spanishText)
        all_inserts.append(insert + polishText + "' , '" + spanishText + poziom)
        all_stats.append(stats_sql + str(record_index) + ",0.0,0.0,1);")
        record_index = record_index + 1
        spanish = False
        polish = False

for sql in all_inserts:
    print(sql)

for sql in all_stats:
    print(sql)
