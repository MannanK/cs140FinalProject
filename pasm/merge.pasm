LOD FF
SUBI 1
STO F0
LODN F0
SUBI 2
STO F1
CMPL F1
JMZI E
LOD FF
SUBI 3
STO F0
LODN F0
STO F0
JUMP F0
LOD FF
SUBI 2
STO F1
LODI 22
STON FF
LOD FF
ADDI 1
STO FF
LODN F1
STON FF
LOD FF
ADDI 1
STO FF
LODN F0
DIVI 2
STON FF
LOD FF
ADDI 1
STO FF
JMPI 0
LOD FF
SUBI 1
STO F0
SUBI 3
STO F1
SUBI 1
STO F2
ADDI 2
STO FF
LODI 3D
STON FF
LOD FF
ADDI 1
STO FF
LODN F0
ADDN F2
STON FF
LOD FF
ADDI 1
STO FF
LODN F1
SUBN F0
STON FF
LOD FF
ADDI 1
STO FF
JMPI 0
LOD FF
SUBI 1
STO FF
LODN FF
STO FC
LOD FF
SUBI 1
STO FF
LODN FF
STO FA
STO FB
ADD FC
STO FC
LOD FF
SUBI 1
STO FF
STO FD
SUBI 1
STO F0
LODN F0
STO FE
LOD F0
SUBI 1
STO F0
LODN F0
STO F8
STO F9
LOD F9
SUB FA
JMZI 72
LOD FB
SUB FC
JMZI 7E
LODN F9
SUBN FB
STO F0
CMPL F0
JMZI 69
LODN F9
STON FD
LOD F9
ADDI 1
STO F9
JMPI 6E
LODN FB
STON FD
LOD FB
ADDI 1
STO FB
LOD FD
ADDI 1
STO FD
JMPI 58
LOD FB
SUB FC
JMZI 8A
LODN FB
STON FD
LOD FB
ADDI 1
STO FB
LOD FD
ADDI 1
STO FD
JMPI 72
LOD F9
SUB FA
JMZI 8A
LODN F9
STON FD
LOD F9
ADDI 1
STO F9
LOD FD
ADDI 1
STO FD
JMPI 7E
LOD FF
STO FD
LOD FE
JMZI 9A
LODN FD
STON F8
LOD FD
ADDI 1
STO FD
LOD F8
ADDI 1
STO F8
LOD FE
SUBI 1
STO FE
JMPI 8C
LOD FF
SUBI 3
STO F0
LODN F0
STO F0
JUMP F0
NOP
HALT
ENDCODE
0 A1
1 A0
2 40
FF 3
A0 47
A1 54
A2 39
A3 42
A4 5
A5 45
A6 63
A7 40
A8 20
A9 48
AA 60
AB 17
AC 52
AD 32
AE 12
AF 27
B0 51
B1 49
B2 50
B3 36
B4 6
B5 7
B6 28
B7 15
B8 19
B9 64
BA 30
BB 21
BC 56
BD 4
BE 43
BF 24
C0 44
C1 13
C2 59
C3 58
C4 8
C5 23
C6 14
C7 34
C8 3
C9 53
CA 18
CB 62
CC 33
CD 37
CE 61
CF 10
D0 2
D1 29
D2 46
D3 57
D4 38
D5 41
D6 1
D7 26
D8 16
D9 35
DA 11
DB 55
DC 22
DD 9
DE 25
DF 31
