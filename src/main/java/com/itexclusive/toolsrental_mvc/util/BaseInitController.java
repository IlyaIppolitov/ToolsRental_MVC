package com.itexclusive.toolsrental_mvc.util;

import com.itexclusive.toolsrental_mvc.model.dao.repositories.UserRepository;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.BrandService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.CategoryService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.ItemService;
import com.itexclusive.toolsrental_mvc.model.dao.services.interfaces.StockPositionService;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Brand;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Category;
import com.itexclusive.toolsrental_mvc.model.entities.shop.Item;
import com.itexclusive.toolsrental_mvc.model.entities.shop.StockPosition;
import com.itexclusive.toolsrental_mvc.model.security.Role;
import com.itexclusive.toolsrental_mvc.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/s")
@RequiredArgsConstructor
public class BaseInitController {
    private final CategoryService categoryService;
    private final UserRepository repo;
    private final BrandService brandService;
    private final ItemService itemService;
    private final StockPositionService stockPositionService;

    private final Random random = new Random();

    @GetMapping("/gen")
    private String baseInit() {
        usersInit();
        categoryInit();
        brandInit();
        itemsInit();
        return "redirect:/index.html";
    }

    private void categoryInit() {
        categoryService.save(Category.builder().name("Болгарка").description("Шлифовальная машина для резки, шлифования и зачистки изделий из камня, металла и других материалов").build());
        categoryService.save(Category.builder().name("Штроборез").description("Электроинструмент для устройства технических штроб (канавок или борозд) в стенах, полах и потолках").build());
        categoryService.save(Category.builder().name("Дрель").description("Электрический инструмент, предназначенный для придачи вращательного движения сверлу или другому режущему инструменту для сверления отверстий в различных материалах при проведении строительных, отделочных, столярных, слесарных и других работ").build());
        categoryService.save(Category.builder().name("Измерительный инструмент").description("Средство измерений, предназначенное для получения значений измеряемой физической величины в установленном диапазоне").build());
        categoryService.save(Category.builder().name("Лобзиковая пила").description("Ручной электроинструмент для распиливания различных материалов с возвратно-поступательным движением пильного полотна").build());
        categoryService.save(Category.builder().name("Перфоратор").description("Ручная ударная машина для обработки строительных материалов. Никогда не давайте соседям!").build());
        categoryService.save(Category.builder().name("Торцовочная пила").description("Электрическое стусло — циркулярная (круглая) пила с электрическим сетевым (редко — аккумуляторным) приводом для поперечного распила длинных заготовок как под прямым углом, так и под произвольно выбранным углом").build());
        categoryService.save(Category.builder().name("Циркулярная пила").description("Режущий инструмент в виде плоского металлического диска, на внешней кромке которого расположены зубья").build());
        categoryService.save(Category.builder().name("Шлифовальная машина").description("Класс электроинструментов для шлифования и полирования поверхностей из различных материалов: древесины, металла, пластмассы, камня и других видов").build());
    }

    private void brandInit() {
        brandService.save(Brand.builder().name("Bosch").build());
        brandService.save(Brand.builder().name("Makita").build());
    }

    private void itemsInit(){
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("GWX 10-125")
            .price(500.)
            .brand(brandService.findByName("Bosch").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("GWX 14-125")
            .price(700.)
            .brand(brandService.findByName("Bosch").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("PWS 650-125")
            .price(300.)
            .brand(brandService.findByName("Bosch").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("9558HN")
            .price(400.)
            .brand(brandService.findByName("Makita").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("GA5030RX9")
            .price(450.)
            .brand(brandService.findByName("Makita").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
        itemService.save(Item.builder()
            .article(Integer.toString(random.nextInt(1_000_000) + 1_000_000))
            .model("9565CVR")
            .price(1000.)
            .brand(brandService.findByName("Makita").get())
            .category(categoryService.findByName("Болгарка").get())
            .position(StockPosition.builder()
                .amount(random.nextInt(900) + 100)
                .build())
            .build());
    }

    private void usersInit() {
        repo.save(new User(
            "admin",
            "admin@admin.com",
            Role.ROLE_ADMIN,
            "$2a$12$7fxWSKkMGjcGTCj3tlrdSODKAPpsqQFW1/jvJUZ/ZNMkveFXT6N/u"));
        repo.save(new User(
            "user",
            "user@user.com",
            Role.ROLE_USER,
            "$2a$12$7fxWSKkMGjcGTCj3tlrdSODKAPpsqQFW1/jvJUZ/ZNMkveFXT6N/u"));
        repo.save(new User(
            "employee",
            "employee@employee.com",
            Role.ROLE_EMPLOYEE,
            "$2a$12$7fxWSKkMGjcGTCj3tlrdSODKAPpsqQFW1/jvJUZ/ZNMkveFXT6N/u"));
    }
}
