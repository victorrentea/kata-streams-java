package victor.training.stream;

public class Yatzy {

  private final int[] dice;

  public Yatzy(int... dice) {
    if (dice.length != 5)
      throw new IllegalArgumentException("You must throw exactly 5 dice");
    this.dice = dice;
  }

  public int chance() {
    return dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
  }

  public int ones() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      if (dice[i] == 1) {
        sum += 1;
      }
    }
    return sum;
  }

  public int twos() {
    int sum = 0;
    if (dice[0] == 2) sum += 2;
    if (dice[1] == 2) sum += 2;
    if (dice[2] == 2) sum += 2;
    if (dice[3] == 2) sum += 2;
    if (dice[4] == 2) sum += 2;
    return sum;
  }

  public int threes() {
    int s = 0;
    if (dice[0] == 3) s += 3;
    if (dice[1] == 3) s += 3;
    if (dice[2] == 3) s += 3;
    if (dice[3] == 3) s += 3;
    if (dice[4] == 3) s += 3;
    return s;
  }

  public int fours() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      if (dice[i] == 4) {
        sum += 4;
      }
    }
    return sum;
  }

  public int fives() {
    int s = 0;
    int i;
    for (i = 0; i < dice.length; i++)
      if (dice[i] == 5)
        s = s + 5;
    return s;
  }

  public int sixes() {
    int sum = 0;
    for (int at = 0; at < dice.length; at++)
      if (dice[at] == 6)
        sum = sum + 6;
    return sum;
  }

  public int one_pair() {
    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    for (int at = 0; at != 6; at++)
      if (counts[6 - at - 1] >= 2)
        return (6 - at) * 2;
    return 0;
  }

  public int two_pair() {
    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    int n = 0;
    int score = 0;
    for (int i = 0; i < 6; i += 1)
      if (counts[6 - i - 1] >= 2) {
        n++;
        score += (6 - i);
      }
    if (n == 2)
      return score * 2;
    else
      return 0;
  }

  public int four_of_a_kind() {
    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    for (int i = 0; i < 6; i++)
      if (counts[i] >= 4)
        return (i + 1) * 4;
    return 0;
  }

  public int three_of_a_kind() {
    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    for (int i = 0; i < 6; i++)
      if (counts[i] >= 3)
        return (i + 1) * 3;
    return 0;
  }

  public int smallStraight() {
int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    if (counts[0] == 1 &&
        counts[1] == 1 &&
        counts[2] == 1 &&
        counts[3] == 1 &&
        counts[4] == 1)
      return 15;
    return 0;
  }

  public int largeStraight() {
    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;
    if (counts[1] == 1 &&
        counts[2] == 1 &&
        counts[3] == 1 &&
        counts[4] == 1
        && counts[5] == 1)
      return 20;
    return 0;
  }

  public int fullHouse() {
    boolean _2 = false;
    int i;
    int _2_at = 0;
    boolean _3 = false;
    int _3_at = 0;


    int[] counts = new int[6];
    counts[dice[0] - 1]++;
    counts[dice[1] - 1]++;
    counts[dice[2] - 1]++;
    counts[dice[3] - 1]++;
    counts[dice[4] - 1]++;

    for (i = 0; i != 6; i += 1)
      if (counts[i] == 2) {
        _2 = true;
        _2_at = i + 1;
      }

    for (i = 0; i != 6; i += 1)
      if (counts[i] == 3) {
        _3 = true;
        _3_at = i + 1;
      }

    if (_2 && _3)
      return _2_at * 2 + _3_at * 3;
    else
      return 0;
  }

  public int yatzy() {
    int[] counts = new int[6];
    for (int die : dice)
      counts[die - 1]++;
    for (int i = 0; i != 6; i++)
      if (counts[i] == 5)
        return 50;
    return 0;
  }
}


