package rippin.bullyscraft.nms.v1_8_R3.Entities;


import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.World;

public class CustomWither extends EntityWither implements CustomEntity {

    public CustomWither(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}

}
