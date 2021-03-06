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
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.A = (float) (Math.random() * 360.0D);
        this.x = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.y = 0.20000000298023224D;
        this.z = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    public OEntityItem(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        this(oworld, d0, d1, d2);
        this.a(oitemstack);
    }

    protected boolean e_() {
        return false;
    }

    public OEntityItem(OWorld oworld) {
        super(oworld);
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
    }

    protected void a() {
        this.v().a(10, 5);
    }

    public void l_() {
        super.l_();
        if (this.b > 0) {
            --this.b;
        }

        boolean tmpTouchesGround = this.I; // CanaryMod
        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.y -= 0.03999999910593033D;
        this.Z = this.i(this.u, (this.E.b + this.E.e) / 2.0D, this.w);
        this.d(this.x, this.y, this.z);
        boolean flag = (int) this.r != (int) this.u || (int) this.s != (int) this.v || (int) this.t != (int) this.w;

        if (flag || this.ac % 25 == 0) {
            if (this.q.g(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) == OMaterial.i) {
                this.y = 0.20000000298023224D;
                this.x = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.z = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.a("random.fizz", 0.4F, 2.0F + this.ab.nextFloat() * 0.4F);
            }

            if (!this.q.I) {
                this.e();
            }
        }

        float f = 0.98F;

        if (this.F) {
            f = 0.58800006F;
            int i = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.E.b) - 1, OMathHelper.c(this.w));

            if (i > 0) {
                f = OBlock.s[i].cV * 0.98F;
            }

            // CanaryMod start
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouchesGround) {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_TOUCH_GROUND, item)) {
                    this.x(); // kill the item
                }
            }// CanaryMod end
        }

        this.x *= (double) f;
        this.y *= 0.9800000190734863D;
        this.z *= (double) f;
        if (this.F) {
            this.y *= -0.5D;
        }

        ++this.a;
        if (!this.q.I && this.a >= 6000) {
            // CanaryMod onEntityDespawn
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
                this.x();
            } else {
                this.b = 0;
            }
        }
    }

    private void e() {
        Iterator iterator = this.q.a(OEntityItem.class, this.E.b(0.5D, 0.0D, 0.5D)).iterator();

        while (iterator.hasNext()) {
            OEntityItem oentityitem = (OEntityItem) iterator.next();

            this.a(oentityitem);
        }
    }

    public boolean a(OEntityItem oentityitem) {
        if (oentityitem == this) {
            return false;
        } else if (oentityitem.T() && this.T()) {
            OItemStack oitemstack = this.d();
            OItemStack oitemstack1 = oentityitem.d();

            if (oitemstack1.b() != oitemstack.b()) {
                return false;
            } else if (oitemstack1.p() ^ oitemstack.p()) {
                return false;
            } else if (oitemstack1.p() && !oitemstack1.q().equals(oitemstack.q())) {
                return false;
            } else if (oitemstack1.b().n() && oitemstack1.k() != oitemstack.k()) {
                return false;
            } else if (oitemstack1.b < oitemstack.b) {
                return oentityitem.a(this);
            } else if (oitemstack1.b + oitemstack.b > oitemstack1.e()) {
                return false;
            } else {
                oitemstack1.b += oitemstack.b;
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
        return this.q.a(this.E, OMaterial.h, (OEntity) this);
    }

    protected void e(int i) {
        this.a(ODamageSource.a, (float) i);
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ar()) {
            return false;
        } else if (this.d() != null && this.d().d == OItem.bU.cv && odamagesource.c()) {
            return false;
        } else {
            this.K();
            this.d = (int) ((float) this.d - f);
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

    public void b_(OEntityPlayer oentityplayer) {
        if (!this.q.I) {
            OItemStack oitemstack = this.d();
            int i = oitemstack.b;

            // CanaryMod: First simulate the pickup and call the hooks
            if (this.b == 0 && oentityplayer.bn.canPickup(this)){
                if(oentityplayer.bn.a(oitemstack)) {
                    if (oitemstack.d == OBlock.O.cF) {
                        oentityplayer.a((OStatBase) OAchievementList.g);
                    }
                }

                if (oitemstack.d == OItem.aH.cv) {
                        oentityplayer.a((OStatBase) OAchievementList.t);
                    }

                if (oitemstack.d == OItem.p.cv) {
                        oentityplayer.a((OStatBase) OAchievementList.w);
                    }

                if (oitemstack.d == OItem.bq.cv) {
                        oentityplayer.a((OStatBase) OAchievementList.z);
                    }

                this.a("random.pop", 0.2F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                oentityplayer.a((OEntity) this, i);
                if (oitemstack.b <= 0) {
                    this.x();
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
        if (!this.q.I) {
            this.e();
        }
    }

    public OItemStack d() {
        OItemStack oitemstack = this.v().f(10);

        if (oitemstack == null) {
            if (this.q != null) {
                this.q.Y().c("Item entity " + this.k + " has no item?!");
            }

            return new OItemStack(OBlock.y);
        } else {
            return oitemstack;
        }
    }

    public void a(OItemStack oitemstack) {
        this.v().b(10, oitemstack);
        this.v().h(10);
    }

    // CanaryMod start: add getEntity
    @Override
    public ItemEntity getEntity() {
        return item;
    } // CanaryMod end
}
