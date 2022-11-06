import pdfplumber
pytanie=True
odpA=False
odpB=False
odpC=False
odpD=False
questions=[]
question={"Pytanie":"","odpA":"","odpB":"","odpC":"","odpD":"","correct":""}
with pdfplumber.open('Zakazy2020.pdf') as pdf: 
    text = pdf.pages[0]
    # clean_text = text.filter(lambda obj: obj["object_type"] == "char" and "Bold" in obj["fontname"])
    clean_text = text.filter(lambda obj: obj["object_type"] == "char")
    all_bold_text = clean_text.filter(lambda obj: "Bold" in obj["fontname"])
    bold_textes = all_bold_text.extract_text().splitlines()
    for line in clean_text.extract_text().splitlines():
        if ";" in line:
            pytanie=True
            questions.append(question)
            question={"Pytanie":"","odpA":"","odpB":"","odpC":"","odpD":"","correct":""}
            
            continue
        if pytanie:
            if line[0].lower()=="a":
                odpA = True
                pytanie = False
            else:
                question["Pytanie"]+=line
                
                continue
        if odpA:
            if line[0].lower()=="b":
                odpB = True
                odpA = False
            else:
                question["odpA"]+=line
                if line in bold_textes:
                    question["correct"]+="1"
                
                continue
        if odpB:
            if line[0].lower()=="c":
                odpC = True
                odpB = False
            else:
                question["odpB"]+=line
                if line in bold_textes:
                    question["correct"]+="2"
                
                continue
        if odpC:
            if line[0].lower()=="d":
                odpD = True
                odpC = False
            else:
                question["odpC"]+=line
                if line in bold_textes:
                    question["correct"]+="3"
                
                continue
        if odpD:
            if line[0].lower()=="e":
                odpE = True
                odpD = False
            else:
                question["odpD"]+=line
                if line in bold_textes:
                    question["correct"]+="4"
                
                continue
    print(questions)
        # if line in bold_textes:
        #     print(line)
        
    # print(clean_text.extract_text())
