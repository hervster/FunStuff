# Author : Herve Nyemeck, Jessy Ma <3
# Randomly pick a line from pre-existing file
# Output said line and send to user

#Album_List = open("Albums", "r") 
# print (Album_List)
from random import randint
albumListFile = "Test.txt"
oldAlbumsListFile = "TestOld.txt"

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
print(album)
file.close()
#print (file.readlines())

#runItBack()