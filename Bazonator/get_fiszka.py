from easyocr import Reader
import easyocr
import os

reader_en_pl = Reader(["pl"])
reader_en_es = Reader(["es"])


def read_text(image_name, model_name, in_line=True):
    # Read the data
    text = model_name.readtext(image_name, detail=0, paragraph=in_line)

    # Join texts writing each text in new line
    return "\n".join(text)


# Load the model
directory = "./Spanish/04_mowisz_po_hiszpansku/"
files = os.listdir(directory)
files.sort()
secondFile = False

idKategorii = "4, "
idZestawu = "1, '"
poziom = "', 'A1', "
nagranie = "'starter_0"
record_index = 35
# "INSERT INTO Fiszka (IdKategori, IdZestawu, Polski, Komentarz,Uzycie Polski, Obcy, UzycieObcy,Poziom,Nagranie) VALUES (0,1,2,3,4,5,6,7,8,9);"
insert_sql = "INSERT INTO Fiszka (IdKategori, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES ("
all_inserts = []
stats_sql = "INSERT INTO Statystyki VALUES("
# ,0.0,0.0,1);"
all_stats = []
for file in files:
    if not secondFile:
        filePL = directory + file
        secondFile = True
    else:
        print(record_index)
        fileES = directory + file
        pl_text = read_text(filePL, reader_en_pl)
        polski = pl_text[pl_text.find("\n") + 2 :]
        es_text = read_text(fileES, reader_en_es)
        espanol = es_text[es_text.find("\n") + 2 :]
        secondFile = False
        all_inserts.append(
            insert_sql
            + idKategorii
            + idZestawu
            + polski
            + espanol
            + poziom
            + nagranie
            + str(record_index)
            + "');"
        )
        all_stats.append(stats_sql + str(record_index) + ",0.0,0.0,1);")
        record_index = record_index + 1

for insert in all_inserts:
    print(insert)

for stat in all_stats:
    print(stat)
