package com.siesque.ui;

import com.siesque.Vanitech;
import com.siesque.ui.alloy_furnace.AlloyFurnaceMenu;
import com.siesque.ui.alloy_furnace.AlloyFurnaceScreen;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class VanitechMenuTypes {
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Vanitech.MOD_ID,
            Registries.MENU);

    public static RegistrySupplier<MenuType<AlloyFurnaceMenu>> ALLOY_FURNACE;

    public static void init() {
        ALLOY_FURNACE = registerMenuType("alloy_furnace",
                () -> new MenuType<>(AlloyFurnaceMenu::new, FeatureFlagSet.of()));

        MENU_TYPES.register();

        ClientLifecycleEvent.CLIENT_STARTED.register(client -> {
            MenuRegistry.registerScreenFactory(ALLOY_FURNACE.get(), AlloyFurnaceScreen::new);
        });
    }

    private static <T extends MenuType<?>> RegistrySupplier<T> registerMenuType(String name, Supplier<T> menuType) {
        return MENU_TYPES.register(ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name), menuType);
    }
}
