package rippin.bullyscraft.nms.v1_8_R3.Entities;


import net.minecraft.server.v1_8_R3.EntitySquid;
import net.minecraft.server.v1_8_R3.World;

public class CustomSquid extends EntitySquid implements CustomEntity {

    public CustomSquid(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}
}
