import pdfplumber
import sqlite3
from sqlite3 import Error


def get_questions_from_pdf(pdfPath):
    pytanie = True
    odpA = False
    odpB = False
    odpC = False
    odpD = False
    odpE = False
    questions = []
    question = {
        "Pytanie": "",
        "odpA": "",
        "odpB": "",
        "odpC": "",
        "odpD": "",
        "odpE": None,
        "correct": "",
    }
    firstline = True
    with pdfplumber.open(pdfPath) as pdf:

        all_text = []
        all_bold_textes = []

        for page in range(len(pdf.pages)):
            text = pdf.pages[page]
            clean_text = text.filter(lambda obj: obj["object_type"] == "char")
            all_bold_text = clean_text.filter(lambda obj: "Bold" in obj["fontname"])
            all_text.extend(clean_text.extract_text().splitlines())
            all_bold_textes.extend(all_bold_text.extract_text().splitlines())
        for line in all_text:
            if "$" in line:
                pytanie = True
                questions.append(question)
                question = {
                    "Pytanie": "",
                    "odpA": "",
                    "odpB": "",
                    "odpC": "",
                    "odpD": "",
                    "odpE": None,
                    "correct": "",
                }
                firstline = True
                continue
            if pytanie:
                if line.lstrip()[0].lower() == "a":
                    firstline = True
                    odpA = True
                    pytanie = False
                else:
                    question["Pytanie"] += line
                    firstline = False
                    continue
            if odpA:
                if line.lstrip()[0].lower() == "b":
                    odpB = True
                    odpA = False
                    firstline = True
                else:
                    question["odpA"] += line
                    if (
                        line in all_bold_textes
                        and question["correct"] == ""
                        and firstline
                    ):
                        question["correct"] += "1"
                    firstline = False
                    continue
            if odpB:
                if line.lstrip()[0].lower() == "c":
                    odpC = True
                    odpB = False
                    firstline = True
                else:
                    question["odpB"] += line
                    if (
                        line in all_bold_textes
                        and question["correct"] == ""
                        and firstline
                    ):
                        question["correct"] += "2"
                    firstline = False
                    continue
            if odpC:
                if line.lstrip()[0].lower() == "d":
                    odpD = True
                    odpC = False
                    firstline = True
                else:
                    question["odpC"] += line
                    if (
                        line in all_bold_textes
                        and question["correct"] == ""
                        and firstline
                    ):
                        question["correct"] += "3"
                    firstline = False
                    continue
            if odpD:
                if line.lstrip()[0].lower() == "e":
                    odpE = True
                    odpD = False
                    firstline = True
                    question["odpE"] = ""
                else:
                    question["odpD"] += line
                    if (
                        line in all_bold_textes
                        and question["correct"] == ""
                        and firstline
                    ):
                        question["correct"] += "4"
                    firstline = False
                    continue
            if odpE:
                if line.lstrip()[0].lower() == "f":
                    odpF = True
                    odpE = False
                    firstline = True
                else:
                    question["odpE"] += line
                    if (
                        line in all_bold_textes
                        and question["correct"] == ""
                        and firstline
                    ):
                        question["correct"] += "5"
                    firstline = False
                    continue
    return questions


def create_connection(db_file):
    """create a database connection to the SQLite database
        specified by db_file
    :param db_file: database file
    :return: Connection object or None
    """
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)

    return conn


def create_subject(conn, subject):
    sql = """ INSERT INTO Przedmiot(idPrzedmiotu,NazwaPrzedmiotu)
              VALUES(?,?) """
    cur = conn.cursor()
    cur.execute(sql, subject)
    conn.commit()
    return cur.lastrowid


def create_question(conn, question):
    sql = """ INSERT INTO Pytanie(idPrzedmiotu,TrescPytania,OdpA,OdpB,OdpC,OdpD,OdpE,PrawidlowaOdp,Rok)
              VALUES(?,?,?,?,?,?,?,?,?) """
    # sql = """ INSERT INTO Pytanie(idPrzedmiotu,TrescPytania,OdpA,OdpB,OdpC,OdpD,OdpE,PrawidlowaOdp)
    #           VALUES(?,?,?,?,?,?,?,?) """
    cur = conn.cursor()
    cur.execute(sql, question)
    conn.commit()
    return cur.lastrowid


def create_stats(conn, stats):
    sql = """ INSERT INTO Statystyki(IdPytania,IloscBlednychOdp,IloscPrawidlowychOdp,CzyNauczone)
              VALUES(?,?,?,?) """
    cur = conn.cursor()
    cur.execute(sql, stats)
    conn.commit()
    return cur.lastrowid


def get_last_row_num(conn, table):
    sql = "select seq from sqlite_sequence where name = ?"
    cur = conn.cursor()
    cur.execute(sql, (table,))
    result = cur.fetchall()
    return result[0][0]


def main():
    pdfName = "Hematologia/BAZA-HEMATOLOGIA.pdf"
    year = 2021
    database = "MedBaza.db"
    subjectId = 2

    subjectName = "Hematologia"
    questions = get_questions_from_pdf(pdfName)
    c = create_connection(database)
    with c:
        IndexPytanie = get_last_row_num(c, "Pytanie")
        # IndexPytanie = 0
        subject = (subjectId, subjectName)
        # create_subject(c, subject)
        for question in questions:
            IndexPytanie = int(IndexPytanie) + 1
            pytanie = (
                subjectId,
                question["Pytanie"],
                question["odpA"],
                question["odpB"],
                question["odpC"],
                question["odpD"],
                question["odpE"],
                question["correct"],
                year,
            )
            create_question(c, pytanie)
            create_stats(c, (IndexPytanie, 0, 0, 0))


if __name__ == "__main__":
    main()
