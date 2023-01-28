
public class Test {

	public boolean m1() {
		if (5 > 6) {
			return true;
		}
		return false;
	}

	public void m2() {
		if (5 > 6) {
			throw new IllegalStateException();
		}
		throw new IllegalStateException();
	}
}
