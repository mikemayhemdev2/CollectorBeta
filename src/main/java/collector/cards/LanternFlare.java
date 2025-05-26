
package collector.cards;

import collector.effects.ColoredSanctityEffect;
import collector.powers.DoomPower;
import collector.powers.LanternFlarePower;
import collector.util.CollectorOrangeTextInterface;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;
import static utilityClasses.Wiz.atb;

public class LanternFlare extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(LanternFlare.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 12, 3, , , 12, 3


    public LanternFlare() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 12;
        baseSecondMagic = secondMagic = 3;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ColoredSanctityEffect(m.hb.cX, m.hb.cY, Color.CHARTREUSE.cpy())));
        applyToEnemy(m, new DoomPower(m, magicNumber));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (pyredKindling) {
                    applyToEnemy(m, new LanternFlarePower(m, secondMagic));
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(1);
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }

    @Override
    public void triggerOnGlowCheck() {//Common glowy effect for Fueled cards.
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !DFL.pl().hand.isEmpty()) {
            if (DFL.pl().hand.group.stream().anyMatch(c -> c.tags.contains(expansionContentMod.KINDLING))) {
                this.glowColor = pyreOrange;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}

