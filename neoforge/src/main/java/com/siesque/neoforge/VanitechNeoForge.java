package com.siesque.neoforge;

import com.siesque.Vanitech;
import net.neoforged.fml.common.Mod;

@Mod(Vanitech.MOD_ID)
public final class VanitechNeoForge {
    public VanitechNeoForge() {
        // Run our common setup.
        Vanitech.init();
    }
}
