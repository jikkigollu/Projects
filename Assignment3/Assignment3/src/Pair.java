	public class Pair<F,S>
	{
		public F first;
		public S second;
		public String f;
		public String s;
		public Pair(F first,S second)
		{
			this.first=first;
			this.second=second;
			f=first.toString();
			s=second.toString();
		}
		public String toString() {
			return f+" "+s;
		}
	}