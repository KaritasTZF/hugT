# hugT
Readme skrá

## Github skipanir:
### Fá nýjar breytingar frá öðrum inn í tölvuna:
Keyra skipanir:
```
git fetch origin
git merge origin
```
### Tjekka staðan á breytingum, sjá hvernig git les skrárnar:
Keyra skipunina:
```
git status
```
Þá prentast út einhver texti eins og
```
On branch main
Your branch is up to date with 'origin/main'.

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")
```

Skrár geta verið annaðhvort Modified, 
### Til að senda þínar breytingar inn á github:
Fyrst skrifa kóða, edita það sem þarf að breyta
Segjum að þú varst að edita og vista skránna `User.java`. Skrár sem hafa verið breyttar eru með M fyrir modified og eru svona ljósbleikar eða ljósappelsínugular (í vscode amk). Við viljum vera viss að adda þau í git með skipuninni
```
git add User.java
```
Nú í `git status` ætti skráin að vera græn. Nú veit git að fylgjast með skránni og að þú hefur áhuga á skránni. Næst þarf að commit-a skránni með smá texta um hverju hefur verið breytt.
```
git commit User.java -m "message"
```
Inn í message strengnum er til dæmis "Lagaði getName methodinu", bara til að útskýra breytinguna aðeins þannig að það þarf ekki að lesa kóðann til að skilja hvað er í gangi.