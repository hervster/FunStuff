# Author : Herve Nyemeck
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
import sys

albumListFile = "Albums to Listen to at Work.txt"
oldAlbumsListFile = "OldAlbums.txt"
today = "Today.txt"

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

#if lines = []:                     Add statement for if albums are full/exist in old albums file
#    print("Out of Albums")
# else:

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
sys.exit()
#print (file.readlines())

# msg = EmailMessage()
#runItBack()