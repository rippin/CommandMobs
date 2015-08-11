package rippin.bullyscraft.nms.v1_8_R3.Entities;


import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.World;

public class CustomWolf extends EntityWolf implements CustomEntity {

    public CustomWolf(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}

}
