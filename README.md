# Java
Jest to repozytorium, w którym znajdziesz projekty wykonane przeze mnie w języku Java.

W folderze 
  NAI (- Narzędzia sztucznej inteligencji) znajdują się przykładowe implementacje algorytmów.
  SKJ (- Sieci komputerowe) jest podstawową implementacją port knocking.
                            Projekt ten zawiera dwa pakiety
                            1. server - zawiera plik, który jest tworzony po utworzeniu obiektu
                            klasy. Tworzymy również tablice Listenerów (klasa znajdująca się
                            w pakiecie server), które dziedziczą po Threadzie. Każdy obiekt w
                            tej tablicy ma własny Socket, który nasłuchuje. Można włączyć pare
                            Listenerów naraz, aby działały w Multithreadingu.
                            2. client - wysyła pakiet z wyspecyfikowanego adresu ip portu.
                            Poprzez nowy DatagramSocket wysyla pakiet, nazwe pliku, dlugosc pliku,
                            zawartosc pliku, bądź disconnect()'uje się, gdy nie dostanie odzewu.
                            Na koniec mamy dwa pakiety oraz dwa pliki - jeden bazowy oraz drugi
                            utworzony w trakcie wykonywania sie programu (plik, ktory zostal
                            wyslany do clienta).
  UTP (- Uniwersalne techniki programowania) zawiera projekty, które zawierają ważne techniki programowania w języku Java.
