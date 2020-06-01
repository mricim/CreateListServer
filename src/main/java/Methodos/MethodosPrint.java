package main.java.Methodos;

import java.util.List;

public class MethodosPrint {
    public static void printsCuadro(List<String> listOptions, Integer spacesExtra) {
        printsMenu(null, null, listOptions, spacesExtra);
    }

    public static void printsMenu(String textUsedMenu, List<String> listMenus, List<String> listOptions, Integer spacesExtra) {
        boolean useMenu = true;
        boolean textMenu1 = true;
        if (textUsedMenu == null) {
            textUsedMenu = "?";
            textMenu1 = false;
        }
        if (listMenus == null) {
            useMenu = false;
        }
        if (spacesExtra == null) {
            spacesExtra = 0;
        }
        int characterMax = 0;
        if (useMenu) {
            for (String listMenu : listMenus) {
                if (listMenu != null) {
                    int countMenu = listMenu.length();
                    if (countMenu > characterMax) {
                        characterMax = countMenu;
                    }
                }
            }
        }
        for (String listOption : listOptions) {
            if (listOption != null) {
                int countOption = listOption.length();
                if (countOption > characterMax) {
                    characterMax = countOption;
                }
            }
        }

        characterMax += 2 + spacesExtra;
        String cortes = "\n" + new String(new char[4 + characterMax]).replace('\0', '=');

        if (textMenu1) {
            int textUsedMenuCount = textUsedMenu.length();
            int characterMaxDivX2 = Math.round((characterMax - textUsedMenuCount) / 2) + 1;
            String impar = "";
            if (textUsedMenuCount % 2 == 0) {
                impar = "=";
            }
            System.out.print("\n" + new String(new char[characterMaxDivX2]).replace('\0', '=') + " " + textUsedMenu + " " + new String(new char[characterMaxDivX2]).replace('\0', '=') + impar);
        } else {
            System.out.print(cortes);
        }


        if (useMenu) {
            for (String menu : listMenus) {
                if (menu == null) {
                    System.out.printf("\n| %1$-" + characterMax + "s |", "");
                } else {

                    int width = menu.length();
                    int padStart = Math.round(width + ((characterMax - width)) / 2);
                    int quiza = Math.round((characterMax - width) / 2) + 2;

                    System.out.print("\n" + String.format("| %" + padStart + "s", menu) + String.format("%" + quiza + "s", "|"));

                }
            }

            System.out.print(cortes);
        }
        for (String option : listOptions) {
            if (option == null) {
                System.out.printf("\n| %1$-" + characterMax + "s |", "");
            } else {
                //System.out.printf("\n|%d|", StringUtils.center(option, characterMax));
                System.out.printf("\n| %1$-" + characterMax + "s |", option);
            }
        }
        System.out.println(cortes);
    }
}
