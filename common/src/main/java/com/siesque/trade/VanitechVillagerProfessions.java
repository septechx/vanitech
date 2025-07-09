package com.siesque.trade;

import com.google.common.collect.ImmutableSet;
import com.siesque.Vanitech;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;

public class VanitechVillagerProfessions {
    private static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(Vanitech.MOD_ID, Registries.VILLAGER_PROFESSION);

    public static RegistrySupplier<VillagerProfession> BANKER;

    public static void init() {
        BANKER = VILLAGER_PROFESSIONS.register("banker", () -> new VillagerProfession(
                Component.translatable("entity.vanitech.villager.banker"),
                holder -> holder.is(VanitechPoiTypes.BANKER_KEY),
                holder -> holder.is(VanitechPoiTypes.BANKER_KEY),
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_TOOLSMITH));

        VILLAGER_PROFESSIONS.register();
    }
}
