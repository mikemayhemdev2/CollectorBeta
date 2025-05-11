package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class MiniCurse extends AbstractCollectorCard implements OnPyreCard {
    public final static String ID = makeID(MiniCurse.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public MiniCurse() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        isPyre();
        this.baseMagicNumber = this.magicNumber = 2;
    }
    boolean pyredKindling = false;

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (pyredKindling) {
            if (upgraded) {
                forAllMonstersLiving(q -> {
                    atb(new VFXAction(new FlashAtkImgEffect(q.hb.cX, q.hb.cY, AbstractGameAction.AttackEffect.POISON)));
                    applyToEnemy(q, new WeakPower(q, magicNumber, false));
                    applyToEnemy(q, new VulnerablePower(q, magicNumber, false));
                });
            } else {
                atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.POISON)));
                applyToEnemy(m, new WeakPower(m, magicNumber, false));
                applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
            }
        }
    }

    public void upp() {
//        target = CardTarget.ALL_ENEMY;
//        upgradeMagicNumber(1);
        this.target = CardTarget.ENEMY;
        uDesc();
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
    }
}