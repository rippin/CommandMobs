package rippin.bullyscraft.nms.v1_8_R1.Entities;

import net.minecraft.server.v1_8_R1.EntityCreeper;
import net.minecraft.server.v1_8_R1.World;

public class CustomCreeper extends EntityCreeper implements CustomEntity {

    public CustomCreeper(World world)
    {
        super(world);
        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}
    @Override
    public void move(double d0, double d1, double d2){}

}
