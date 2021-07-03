package fuzzyacornindustries.kindredlegacy.common.particle;

import java.util.Locale;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import fuzzyacornindustries.kindredlegacy.common.core.ModParticleTypes;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class ConfuseParticleData implements IParticleData 
{
    public static final ConfuseParticleData NORMAL = new ConfuseParticleData(0.1f);
    public static final ConfuseParticleData DENSE = new ConfuseParticleData(0.3f);
    
    public static final IDeserializer<ConfuseParticleData> DESERIALIZER = new IDeserializer<ConfuseParticleData>() 
    {
        @Override
        public ConfuseParticleData deserialize(ParticleType<ConfuseParticleData> particleType, StringReader stringReader) throws CommandSyntaxException 
        {
            stringReader.expect(' ');
            float alpha = stringReader.readFloat();
            return new ConfuseParticleData(alpha);
        }

        @Override
        public ConfuseParticleData read(ParticleType<ConfuseParticleData> particleType, PacketBuffer packetBuffer) 
        {
            return new ConfuseParticleData(packetBuffer.readFloat());
        }
    };

    private final float alpha;

    public ConfuseParticleData(float alpha) 
    {
        this.alpha = alpha;
    }

    @Override
    public ParticleType<?> getType() 
    {
        return ModParticleTypes.CONFUSE_PARTICLE.get();
    }

    @Override
    public void write(PacketBuffer packetBuffer) 
    {
        packetBuffer.writeFloat(alpha);
    }

    @Override
    public String getParameters() 
    {
        return String.format(Locale.ROOT, "%s %f", getType().getRegistryName(), alpha);
    }

    public float getAlpha() 
    {
        return alpha;
    }
}