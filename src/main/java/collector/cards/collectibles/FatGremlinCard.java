package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.HeadStompAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import java.util.Objects;

import static collector.CollectorMod.GREMLINGANG;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class FatGremlinCard extends AbstractCollectibleCard {
    public final static String ID = makeID(FatGremlinCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 4, 1, , , 2, 1

    public FatGremlinCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
//        tags.add(expansionContentMod.UNPLAYABLE);
//        tags.add(expansionContentMod.KINDLING);
//        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        DFL.atb(new LaterAction(()->{
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&   //Absolutely miserable check.
                    (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type == CardType.SKILL) {
                addToTop(new ApplyPowerAction(m, DFL.pl(), new WeakPower(m, this.magicNumber, false), this.magicNumber));
            }
        }));
//        addToBot(new HeadStompAction(m, magicNumber));
    }

/*    @Override
    public void triggerOnExhaust() {
        for (AbstractMonster mon : DFL.activeMonsterList()) {
            addToBot(new HeadStompAction(mon, magicNumber));
        }
    }*/

    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()
                && (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

/*    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }*/

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

}