package com.Suzj.extraenchantments.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class PotionRegistryHandler {

    public static final Potion POTION_JINGLINGSHUQING = new PotionJingLingShuQing();
    public static final Potion POTION_YISHANG = new PotionYiShang();
    public static final Potion POTION_TELEPORT_SPAWN = new PotionTeleportSpawn();

    @SubscribeEvent
    public static void onPotionResgistry(RegistryEvent.Register<Potion> event){
        IForgeRegistry<Potion> registry = event.getRegistry();
        registry.register(POTION_JINGLINGSHUQING);
        registry.register(POTION_YISHANG);
        registry.register(POTION_TELEPORT_SPAWN);
    }
}
