# Author : Herve Nyemeck, Jessy Ma <3
# Copy lines from google doc
# Randomly pick a line from pre-existing file
# Output said line and send to user

#Album_List = open("Albums", "r") 
# print (Album_List)
import sendEmail 
from random import randint
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.base import MIMEBase
from datetime import datetime
import os

albumListFile = "Test.txt"
oldAlbumsListFile = "TestOld.txt"
today = "today.txt"

def removePreexisting():
    exists = os.path.isfile(today)
    if exists:
        os.remove(today)
    else:
        return  

removePreexisting()

file = open(albumListFile,"r")
lines = []
for line in file:
    lines.append(line)
size = len(lines)

album=""
def generateAlbum():    
    random_number = randint(0,(size-1))
    global album
    album = lines[random_number]
generateAlbum()
file.close()

old_albums = open(oldAlbumsListFile,"r")
old_albums_list = []

for line in old_albums:
    old_albums_list.append(line)

#def runItBack():
while album in old_albums_list:
    generateAlbum()

old_albums.close()
old_albums = open(oldAlbumsListFile,"a")
old_albums.write(album)
# print(album)
file.close()
today_album = open(today, "w")
today_album.write(album)
today_album.close()
sendEmail.sendEmail()
#print (file.readlines())

# msg = EmailMessage()
#runItBack()