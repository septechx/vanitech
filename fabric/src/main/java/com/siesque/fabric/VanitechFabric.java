package com.siesque.fabric;

import com.siesque.Vanitech;
import com.siesque.fabric.blocks.entities.VanitechBlockEntityTypesFabric;
import net.fabricmc.api.ModInitializer;

public final class VanitechFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Vanitech.init();
        VanitechBlockEntityTypesFabric.init();
    }
}
