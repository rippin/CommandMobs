package rippin.bullyscraft.nms.v1_8_R1.Entities;

import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.World;

public class CustomEndermite extends EntityEndermite implements CustomEntity {

    public CustomEndermite(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}
    @Override
    public void move(double d0, double d1, double d2){}

}
