package rippin.bullyscraft.nms.v1_8_R1.Entities;

import net.minecraft.server.v1_8_R1.EntityCow;
import net.minecraft.server.v1_8_R1.World;

public class CustomCow extends EntityCow implements CustomEntity {

    public CustomCow(World world)
    {
        super(world);
        EntityUtils.setNMSEntityNoAI(this);


    }

    @Override
    public void makeSound(String s, float f, float f1) {}

}
