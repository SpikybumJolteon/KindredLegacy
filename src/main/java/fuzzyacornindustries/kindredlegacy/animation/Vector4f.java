package fuzzyacornindustries.kindredlegacy.animation;

public class Vector4f
{
	private float x;
	private float y;
	private float z;
	private float w;

	// Constructor
	public Vector4f()
	{
		x = y = z = w = 0;
	}

	// Constructor
	public Vector4f(float i, float j, float k, float h)
	{
		x = i;
		y = j;
		z = k;
		w = h;
	}

	// Constructor using a three value vector
	public Vector4f(Vector3f otherVector)
	{
		x = otherVector.getX();
		y = otherVector.getY();
		z = otherVector.getZ();
		w = 1.0F;
	}

	public Vector4f(Vector4f otherVector)
	{
		x = otherVector.getX();
		y = otherVector.getY();
		z = otherVector.getZ();
		w = otherVector.getW();
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}

	public float getW()
	{
		return w;
	}

	// Add two vectors
	public static Vector4f vectorPlusVector(Vector4f vector1, Vector4f vector2)
	{
		return new Vector4f(
				vector1.getX() + vector2.getX(),
				vector1.getY() + vector2.getY(),
				vector1.getZ() + vector2.getZ(),
				vector1.getW() + vector2.getW());
	}

	// Add a float to a vector
	public static Vector4f vectorPlusFloat(Vector4f vector, float val)
	{
		return new Vector4f(
				vector.getX() + val,
				vector.getY() + val,
				vector.getZ() + val,
				vector.getW() + val);
	}

	// Subtract a float from a vector
	public static Vector4f vectorSubtFloat(Vector4f vector, float val)
	{
		return new Vector4f(
				vector.getX() - val,
				vector.getY() - val,
				vector.getZ() - val,
				vector.getW() - val);
	}

	// Subtract one vector from another
	public static Vector4f vectorSubtVector(Vector4f vector1, Vector4f vector2)
	{
		return new Vector4f(
				vector1.getX() - vector2.getX(),
				vector1.getY() - vector2.getY(),
				vector1.getZ() - vector2.getZ(),
				vector1.getW() - vector2.getW());
	}

	// Multiply vector by scalar
	public static Vector4f vectorMultiScalar(Vector4f vector, float scalar)
	{
		return new Vector4f(
				vector.getX() * scalar,
				vector.getY() * scalar,
				vector.getZ() * scalar,
				vector.getW() * scalar);
	}

	// Divide vector by scalar
	public static Vector4f vectorDivScalar(Vector4f vector, float scalar)
	{
		return new Vector4f(
				vector.getX() / scalar,
				vector.getY() / scalar,
				vector.getZ() / scalar,
				vector.getW() / scalar);
	}

	// Multiply a vector by a vector
	public static Vector4f vectorMultiVector(Vector4f vector1, Vector4f vector2)
	{
		return new Vector4f(
				vector1.getX() * vector2.getX(),
				vector1.getY() * vector2.getY(),
				vector1.getZ() * vector2.getZ(),
				vector1.getW() * vector2.getW());
	}

	// Unary negative
	public static Vector4f negative(Vector4f vector)
	{
		return new Vector4f(-vector.getX(), -vector.getY(), -vector.getZ(), -vector.getW());
	}

	// Check for equality
	public boolean equals(Vector4f otherVector)
	{
		return this.getX() == otherVector.getX() &&
				this.getY() == otherVector.getY() &&
				this.getZ() == otherVector.getZ() &&
				this.getW() == otherVector.getW();
	}

	// Check two vectors for equality
	public static boolean vectorEquality(Vector4f vector1, Vector4f vector2)
	{
		return vector1.equals(vector2);
	}

	// Check two vectors are not equal
	public static boolean vectorsNotEqual(Vector4f vector1, Vector4f vector2)
	{
		return !(vector1.equals(vector2));
	}

	/***********************************
	 * Function for debugging purposes *
	 ***********************************/
	// Print the contents of the Vector to the debug display
	public void PrintVector( String debugText )
	{
		System.out.println( debugText );
		System.out.println( Double.toString( this.x ) + ", " + Double.toString( this.y ) + ", " + Double.toString( this.z ) + ", " + Double.toString( this.w ) );
	}
}