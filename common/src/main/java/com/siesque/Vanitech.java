package com.siesque;

import com.siesque.blocks.VanitechBlocks;
import com.siesque.entity.villager.VanitechPoiTypes;
import com.siesque.entity.villager.VanitechTrades;
import com.siesque.entity.villager.VanitechVillagerProfessions;
import com.siesque.items.VanitechItems;
import com.siesque.recipe.VanitechRecipeDisplayTypes;
import com.siesque.recipe.VanitechRecipeSerializers;
import com.siesque.recipe.VanitechRecipeTypes;
import com.siesque.recipe_book.VanitechRecipeBookCategories;
import com.siesque.ui.VanitechMenuTypes;
import org.slf4j.Logger;

public final class Vanitech {
    public static final String MOD_ID = "vanitech";
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        VanitechBlocks.init();
        VanitechItems.init();

        VanitechRecipeTypes.init();
        VanitechRecipeSerializers.init();
        VanitechRecipeDisplayTypes.init();
        VanitechRecipeBookCategories.init();

        VanitechMenuTypes.init();

        VanitechPoiTypes.init();
        VanitechVillagerProfessions.init();

        VanitechTrades.init();
    }
}