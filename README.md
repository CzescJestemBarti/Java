# Java
Jest to repozytorium, w którym znajdziesz projekty wykonane przeze mnie w języku Java.

W folderze 
<p style="margin-left:2em">
<strong>NAI</strong> (- Narzędzia sztucznej inteligencji) znajdują się przykładowe implementacje algorytmów.<br>
<strong>SKJ</strong> (- Sieci komputerowe) jest podstawową implementacją port knocking.
<br>Projekt ten zawiera dwa pakiety
<br>1.<strong> server </strong>- zawiera plik, który jest tworzony po utworzeniu obiektu
klasy. Tworzymy również tablice Listenerów (klasa znajdująca się
w pakiecie server), które dziedziczą po Threadzie. Każdy obiekt w
tej tablicy ma własny Socket, który nasłuchuje. Można włączyć pare
Listenerów naraz, aby działały w Multithreadingu.
<br>2.<strong> client </strong>- wysyła pakiet z wyspecyfikowanego adresu ip portu.
Poprzez nowy DatagramSocket wysyla pakiet, nazwe pliku, dlugosc pliku,
zawartosc pliku, bądź disconnect()'uje się, gdy nie dostanie odzewu.
Na koniec mamy dwa pakiety oraz dwa pliki - jeden bazowy oraz drugi
utworzony w trakcie wykonywania sie programu (plik, ktory zostal
wyslany do clienta).<br>
<strong>UTP</strong> (- Uniwersalne techniki programowania) zawiera projekty, które zawierają ważne techniki programowania w języku Java.
</p>
