package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class FlameLash extends AbstractCollectorCard implements OnPyreCard {
    public final static String ID = makeID(FlameLash.class.getSimpleName());
    // intellij stuff attack, enemy, common, 8, 2, , , 8, 2

    boolean pyredKindling = false;
    public FlameLash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!pyredKindling) {
            if (m != null) {
                this.addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.baseDamage / 5), 0.2F));
            }
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }else{
            for (AbstractMonster mm : DFL.activeMonsterList()){
                dmg (mm, AbstractGameAction.AttackEffect.FIRE);
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {//Common glowy effect for Fueled cards.
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !DFL.pl().hand.isEmpty()) {
            if (DFL.pl().hand.group.stream().anyMatch(c -> c.tags.contains(expansionContentMod.KINDLING))) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)){
            pyredKindling = true;
        }else{
            pyredKindling = false;
        }
        /*
        if (card.baseDamage > 0) {
            toAdd = card.baseDamage;
        } else {
            toAdd = -1;
        }
        */
    }

    public void upp() {
        upgradeDamage(4);
        //upgradeMagicNumber(2);
       //uDesc();
    }
}