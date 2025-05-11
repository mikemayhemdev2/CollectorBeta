package collector.cards;

import collector.CollectorCollection;
import collector.CollectorMod;
import collector.cards.collectibles.AbstractCollectibleCard;
import collector.effects.MiniUpgradeShine;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import java.util.ArrayList;

import static collector.CollectorMod.COLLECTIBLE_CARD_COLOR;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class DarkApotheosis extends AbstractCollectorCard {
    public final static String ID = makeID(DarkApotheosis.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , , 

    public DarkApotheosis() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> toCheck = new ArrayList<>(CollectorCollection.combatCollection.group);

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : toCheck) {
                    q.upgrade();
                }
            }
        });

        if (toCheck.stream().anyMatch(q -> CollectorCollection.combatCollection.group.contains(q))) {
            atb(new VFXAction(new MiniUpgradeShine(CollectorMod.combatCollectionPileButton.getRenderX(), CollectorMod.combatCollectionPileButton.getRenderY())));
        }

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DFL.pl().discardPile.group) {
                    if (c.canUpgrade() && c instanceof AbstractCollectibleCard || !CollectorCollection.collection.isEmpty() && CollectorCollection.collection.contains(c.makeSameInstanceOf())) {
                        c.upgrade();
                        c.applyPowers();
                    }
                }
            }
        });

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DFL.pl().drawPile.group) {
                    if (c.canUpgrade() && c instanceof AbstractCollectibleCard || !CollectorCollection.collection.isEmpty() && CollectorCollection.collection.contains(c.makeSameInstanceOf())) {
                        c.upgrade();
                        c.applyPowers();
                    }
                }
            }
        });

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : DFL.pl().hand.group) {
                    if (c.canUpgrade() && c instanceof AbstractCollectibleCard || !CollectorCollection.collection.isEmpty() && CollectorCollection.collection.contains(c.makeSameInstanceOf())) {
                        c.superFlash(); //Cards in your hand sparkle!
                        c.upgrade();
                        c.applyPowers();
                    }
                }
            }
        });

    }

    public void upp() {
 //       this.isInnate = true;
 //       uDesc();
        upgradeBaseCost(0);
    }
}