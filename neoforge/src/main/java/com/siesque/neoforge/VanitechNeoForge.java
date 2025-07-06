package com.siesque.neoforge;

import com.siesque.Vanitech;
import com.siesque.neoforge.blocks.entities.VanitechBlockEntityTypesNeoForge;
import com.siesque.ui.VanitechMenuTypes;
import com.siesque.ui.alloy_furnace.AlloyBlastFurnaceScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(Vanitech.MOD_ID)
public final class VanitechNeoForge {
    public VanitechNeoForge() {
        Vanitech.init();
        VanitechBlockEntityTypesNeoForge.init();
    }

    @EventBusSubscriber(modid = Vanitech.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(VanitechMenuTypes.ALLOY_BLAST_FURNACE.get(), AlloyBlastFurnaceScreen::new);
        }
    }
}
