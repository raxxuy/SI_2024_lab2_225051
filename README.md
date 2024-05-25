# SI_2024_lab2_225051
Milan Ilkov 225051

# 2. Control Flow Graph

```java
public static boolean checkCart(List<Item> allItems, int payment){ // 1
    if (allItems == null){ // 2
        throw new RuntimeException("allItems list can't be null!"); // 3
    }

    float sum = 0; // 4

    for (int i = 0; i < allItems.size(); i++){ // 5
        Item item = allItems.get(i); // 6
        if (item.getName() == null || item.getName().length() == 0){ // 7
            item.setName("unknown"); // 8
        }
        if (item.getBarcode() != null){ // 9
            String allowed = "0123456789"; // 10
            char chars[] = item.getBarcode().toCharArray(); // 11
            for (int j = 0; j < item.getBarcode().length(); j++){ // 12
                char c = item.getBarcode().charAt(j); // 13
                if (allowed.indexOf(c) == -1){ // 14
                    throw new RuntimeException("Invalid character in item barcode!"); // 15
                }
            }
            if (item.getDiscount() > 0){ // 16
                sum += item.getPrice()*item.getDiscount(); // 17
            }
            else {
                sum += item.getPrice(); // 18
            }
        }
        else {
            throw new RuntimeException("No barcode!"); // 19
        }
        if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){ // 20
            sum -= 30; // 21
        }
    }
    if (sum <= payment){ // 22
        return true; // 23
    }
    else {
        return false; // 24
    }
} // 25
```

![img](https://cdn.discordapp.com/attachments/1054498402592890990/1241729547687297094/cfg.drawio2.png?ex=664b4242&is=6649f0c2&hm=d4cca5ba5c5792531bf35c4dbfb301201faedc86d172817513f017fef62ad5b6&)

# 3. Цикломатска комплексност
Цикломатска комплекност е 10. Најдена е според формулата V(G) = E - N + 2, каде што E е бројот на ребра и N е бројот на јазли.
Графот има 33 ребра (E) и 25 јазли (N), затоа испаѓа 10.

# 4. Every Branch критериумот - тест случаи
Case1: allItems = null, payment = 0<br>
Ги опфаќа јазлите 1, 2, 3 и 25 (allItems е null)<br>

Case2: allItems = [Item(null, null, 10, 0)], payment = 30<br>
Ги опфаќа јазлите 1, 2, 4, 5.1, 5.2, 6, 7, 8, 9, 19 и 25 (име е null, баркод е null)<br>

Case3: allItems = [Item("Item1", "а", 20, 0.5)], payment = 20<br>
Ги опфаќа јазлите 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 15, 25 (невалиден баркод)<br>

Case4: allItems = [Item("Item1", "0", 700, 0.5)], payment = 320<br>
Ги опфаќа јазлите 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 12.3, 16, 17, 20, 21, 5.3, 22, 23, 25 (цена > 300, попуст > 0, баркод што почнува на 0, и сума <= payment)<br>

Case5: allItems = [Item("Item1", "1", 200, 0)], payment = 170<br>
Ги опфаќа јазлите 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 12.3, 16, 18, 20, 5.3, 22, 24, 25 (цена <= 300, нема попуст и сума > плаќањето)<br>

# 5. Multiple Condition критериумот - тест случаи
```
if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
```
Case1: Item("Item1", "0", 600, 0.5) (TTT)<br>
Case2: Item("Item1", "1", 600, 0.5) (TTF)<br>
Case3: Item("Item1", "0", 500, 0) (TFX)<br>
Case4: Item("Item1", "0", 200, 0.4) (FXX)<br>
