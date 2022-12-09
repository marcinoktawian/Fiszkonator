import cv2
import pytesseract
import os

directory = "./Spanish/"
for filename in os.listdir(directory):
    file = directory + filename
    img = cv2.imread(file)
    text = pytesseract.image_to_string(img)
    print(text)
