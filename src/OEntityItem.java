import java.util.Iterator;

public class OEntityItem extends OEntity {

    public int a;
    public int b;
    private int d;
    public float c;
    // CanaryMod Start
    ItemEntity item = new ItemEntity(this);
    // CanaryMod End

    public OEntityItem(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.a = 0;
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.M = this.O / 2.0F;
        this.b(d0, d1, d2);
        this.z = (float) (Math.random() * 360.0D);
        this.w = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.x = 0.20000000298023224D;
        this.y = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    public OEntityItem(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        this(oworld, d0, d1, d2);
        this.a(oitemstack);
    }

    protected boolean f_() {
        return false;
    }

    public OEntityItem(OWorld oworld) {
        super(oworld);
        this.a = 0;
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.M = this.O / 2.0F;
    }

    protected void a() {
        this.v().a(10, 5);
    }

    public void j_() {
        super.j_();
        if (this.b > 0) {
            --this.b;
        }

        boolean tmpTouchesGround = this.E; // CanaryMod
        this.q = this.t;
        this.r = this.u;
        this.s = this.v;
        this.x -= 0.03999999910593033D;
        this.Y = this.i(this.t, (this.D.b + this.D.e) / 2.0D, this.v);
        this.d(this.w, this.x, this.y);
        boolean flag = (int) this.q != (int) this.t || (int) this.r != (int) this.u || (int) this.s != (int) this.v;

        if (flag || this.ab % 25 == 0) {
            if (this.p.g(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) == OMaterial.i) {
                this.x = 0.20000000298023224D;
                this.w = (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F);
                this.y = (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F);
                this.a("random.fizz", 0.4F, 2.0F + this.aa.nextFloat() * 0.4F);
            }

            if (!this.p.I) {
                this.g();
            }
        }

        float f = 0.98F;

        if (this.E) {
            f = 0.58800006F;
            int i = this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.D.b) - 1, OMathHelper.c(this.v));

            if (i > 0) {
                f = OBlock.p[i].cC * 0.98F;
            }

            // CanaryMod start
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouchesGround) {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_TOUCH_GROUND, item)) {
                    this.x(); // kill the item
                }
            }// CanaryMod end
        }

        this.w *= (double) f;
        this.x *= 0.9800000190734863D;
        this.y *= (double) f;
        if (this.E) {
            this.x *= -0.5D;
        }

        ++this.a;
        if (!this.p.I && this.a >= 6000) {
            // CanaryMod onEntityDespawn
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
                this.x();
            } else {
                this.b = 0;
            }
        }
    }

    private void g() {
        Iterator iterator = this.p.a(OEntityItem.class, this.D.b(0.5D, 0.0D, 0.5D)).iterator();

        while (iterator.hasNext()) {
            OEntityItem oentityitem = (OEntityItem) iterator.next();

            this.a(oentityitem);
        }
    }

    public boolean a(OEntityItem oentityitem) {
        if (oentityitem == this) {
            return false;
        } else if (oentityitem.S() && this.S()) {
            OItemStack oitemstack = this.d();
            OItemStack oitemstack1 = oentityitem.d();

            if (oitemstack1.b() != oitemstack.b()) {
                return false;
            } else if (oitemstack1.o() ^ oitemstack.o()) {
                return false;
            } else if (oitemstack1.o() && !oitemstack1.p().equals(oitemstack.p())) {
                return false;
            } else if (oitemstack1.b().l() && oitemstack1.j() != oitemstack.j()) {
                return false;
            } else if (oitemstack1.a < oitemstack.a) {
                return oentityitem.a(this);
            } else if (oitemstack1.a + oitemstack.a > oitemstack1.d()) {
                return false;
            } else {
                oitemstack1.a += oitemstack.a;
                oentityitem.b = Math.max(oentityitem.b, this.b);
                oentityitem.a = Math.min(oentityitem.a, this.a);
                oentityitem.a(oitemstack1);
                this.x();
                return true;
            }
        } else {
            return false;
        }
    }

    public void c() {
        this.a = 4800;
    }

    public boolean I() {
        return this.p.a(this.D, OMaterial.h, (OEntity) this);
    }

    protected void d(int i) {
        this.a(ODamageSource.a, i);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else if (this.d() != null && this.d().c == OItem.bS.cj && odamagesource == ODamageSource.k) {
            return false;
        } else {
            this.K();
            this.d -= i;
            if (this.d <= 0) {
                this.x();
            }

            return false;
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Health", (short) ((byte) this.d));
        onbttagcompound.a("Age", (short) this.a);
        if (this.d() != null) {
            onbttagcompound.a("Item", this.d().b(new ONBTTagCompound()));
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.d = onbttagcompound.d("Health") & 255;
        this.a = onbttagcompound.d("Age");
        ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Item");

        this.a(OItemStack.a(onbttagcompound1));
        if (this.d() == null) {
            this.x();
        }
    }

    public void c_(OEntityPlayer oentityplayer) {
        if (!this.p.I) {
            OItemStack oitemstack = this.d();
            int i = oitemstack.a;

            // CanaryMod: First simulate the pickup and call the hooks
            if (this.b == 0 && oentityplayer.bJ.canPickup(this)) {
                if (oentityplayer.bJ.a(oitemstack)) {
                    if (oitemstack.c == OBlock.M.cm) {
                        oentityplayer.a((OStatBase) OAchievementList.g);
                    }

                    if (oitemstack.c == OItem.aF.cj) {
                        oentityplayer.a((OStatBase) OAchievementList.t);
                    }

                    if (oitemstack.c == OItem.n.cj) {
                        oentityplayer.a((OStatBase) OAchievementList.w);
                    }

                    if (oitemstack.c == OItem.bo.cj) {
                        oentityplayer.a((OStatBase) OAchievementList.z);
                    }

                    this.a("random.pop", 0.2F, ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    oentityplayer.a((OEntity) this, i);
                    if (oitemstack.a <= 0) {
                        this.x();
                    }
                }
            }
        }
    }

    public String an() {
        return OStatCollector.a("item." + this.d().a());
    }

    public boolean aq() {
        return false;
    }

    public void b(int i) {
        super.b(i);
        if (!this.p.I) {
            this.g();
        }
    }

    public OItemStack d() {
        OItemStack oitemstack = this.v().f(10);

        if (oitemstack == null) {
            System.out.println("Item entity " + this.k + " has no item?!");
            return new OItemStack(OBlock.w);
        } else {
            return oitemstack;
        }
    }

    public void a(OItemStack oitemstack) {
        this.v().b(10, oitemstack);
        this.v().h(10);
    }
}
