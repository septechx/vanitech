package com.siesque;

import com.siesque.blocks.VanitechBlocks;
import com.siesque.items.VanitechItems;

public final class Vanitech {
    public static final String MOD_ID = "vanitech";

    public static void init() {
        VanitechBlocks.init();
        VanitechItems.init();
    }
}
