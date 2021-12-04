package com.sparta.fooreats.validate;

public class FoodsValidate {

    public static void MenuExistValid(Boolean existMenu , int price) {
        if (existMenu)
            throw new IllegalArgumentException("이미 등록된 메뉴입니다.");
        else if (price < 100 || price > 1000000 || price % 100 != 0)
             throw new IllegalArgumentException("백원이하, 백만원이상, 100단위 입력이 아닙니다");
    }

}
