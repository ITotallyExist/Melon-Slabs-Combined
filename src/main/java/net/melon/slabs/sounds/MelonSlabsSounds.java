package net.melon.slabs.sounds;


import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MelonSlabsSounds {
    public static SoundEvent FRANKENMELON_HURT_EVENT = new SoundEvent(new Identifier("melonslabs:frankenmelon_hurt"));
    public static SoundEvent PSH_SOUND_EVENT = new SoundEvent(new Identifier("melonslabs:psh"));
    public static SoundEvent PSH_LONG_SOUND_EVENT = new SoundEvent(new Identifier("melonslabs:psh_long"));

    public static void registerSoundEvents(){
        Registry.register(Registry.SOUND_EVENT, "melonslabs:frankenmelon_hurt", FRANKENMELON_HURT_EVENT);
        Registry.register(Registry.SOUND_EVENT, "melonslabs:psh", PSH_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, "melonslabs:psh_long", PSH_LONG_SOUND_EVENT);
    }
}