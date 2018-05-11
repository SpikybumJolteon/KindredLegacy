package fuzzyacornindustries.kindredlegacy.animation;

public class Vector3f
{
	// Vector consisting of three floats
	private float x;
	private float y;
	private float z;

	// Constructor
	public Vector3f()
	{
		x = y = z = 0;
	}

	// Constructor with each x, y, z value manually added
	public Vector3f(float i, float j, float k)
	{
		x = i;
		y = j;
		z = k;
	}

	// Constructor using another vector
	public Vector3f(Vector3f otherVector)
	{
		x = otherVector.x;
		y = otherVector.y;
		z = otherVector.z;
	}

	// Constructor using four value vector
	public Vector3f(Vector4f otherVector)
	{
		x = otherVector.getX();
		y = otherVector.getY();
		z = otherVector.getZ();
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

	public void setX(float newX)
	{
		x = newX;
	}

	public void setY(float newY)
	{
		y = newY;
	}

	public void setZ(float newZ)
	{
		z = newZ;
	}

	public void addToX(float addX)
	{
		x =+ addX;
	}

	public void addToY(float addY)
	{
		y =+ addY;
	}

	public void addToZ(float addZ)
	{
		z =+ addZ;
	}

	// Add new vector to current vector
	public void addVector(Vector3f addVector)
	{
		x = x + addVector.getX();
		y = y + addVector.getY();
		z = z + addVector.getZ();
	}

	// Add two vectors together
	public static Vector3f vectorPlusVector(Vector3f vector1, Vector3f vector2)
	{
		return new Vector3f(
				vector1.getX() + vector2.getX(),
				vector1.getY() + vector2.getY(),
				vector1.getZ() + vector2.getZ());
	}

	// Add a float to each vector value
	public static Vector3f vectorPlusFloat(Vector3f vector, float val)
	{
		return new Vector3f(vector.getX() + val, vector.getY() + val, vector.getZ() + val);
	}

	// Subtract a float from each vector value
	public static Vector3f vectorSubtFloat(Vector3f vector, float val)
	{
		return new Vector3f(vector.getX() - val, vector.getY() - val, vector.getZ() - val);
	}

	// Subtract one vector from another.
	public static Vector3f vectorSubtVector(Vector3f vector1, Vector3f vector2)
	{
		return new Vector3f(
				vector1.getX() - vector2.getX(),
				vector1.getY() - vector2.getY(),
				vector1.getZ() - vector2.getZ());
	}

	// Multiply current vector by a scaler
	public void multiplyVector(float scaler)
	{
		x = x * scaler;
		y = y * scaler;
		z = z * scaler;
	}

	// Multiply a vector by a scalar
	public static Vector3f vectorMultiScalar(Vector3f vector, float scalar)
	{
		return new Vector3f(vector.getX() * scalar, vector.getY() * scalar, vector.getZ() * scalar);
	}

	// Divide a vector by a scalar
	public static Vector3f vectorDivScalar(Vector3f vector, float scalar)
	{
		return new Vector3f(vector.getX() / scalar, vector.getY() / scalar, vector.getZ() / scalar);
	}

	// Multiply a vector by a vector
	public static Vector3f vectorMultiVector(Vector3f vector1, Vector3f vector2)
	{
		return new Vector3f(
				vector1.getX() * vector2.getX(),
				vector1.getY() * vector2.getY(),
				vector1.getZ() * vector2.getZ());
	}

	// Divide a vector by a vector
	public static Vector3f vectorDivVector(Vector3f vector1, Vector3f vector2)
	{
		return new Vector3f(
				vector1.getX() / vector2.getX(),
				vector1.getY() / vector2.getY(),
				vector1.getZ() / vector2.getZ());
	}

	// Unary negative
	public static Vector3f negative(Vector3f vector)
	{
		return new Vector3f(-vector.getX(), -vector.getY(), -vector.getZ());
	}

	public static double distance(Vector3f vector1, Vector3f vector2)
	{
		return (double)Math.sqrt(
				Math.pow(vector1.getX() - vector2.getX(), 2) +
				Math.pow(vector1.getY() - vector2.getY(), 2) +
				Math.pow(vector1.getZ() - vector2.getZ(), 2));
	}

	// Calculate the magnitude of this vector
	public double magnitude()
	{
		return (double)Math.sqrt((x * x) + (y * y) + (z * z));
	}

	// Square this vector
	public double distanceSquared()
	{
		return ((x * x) + (y * y) + (z * z));
	}

	// Convert the vector to its unit form
	public void normalize()
	{
		double tempMagnitude = magnitude();

		x /= tempMagnitude;
		y /= tempMagnitude;
		z /= tempMagnitude;
	}

	// Calculate the cross product of this vector and another vector
	public Vector3f crossProduct(Vector3f vector2)
	{
		return new Vector3f(
				(this.getY() * vector2.getZ()) - (this.getZ() * vector2.getY()),
				(this.getZ() * vector2.getX()) - (this.getX() * vector2.getZ()),
				(this.getX() * vector2.getY()) - (this.getY() * vector2.getX()));
	}

	// Calculate the dot product of this vector and another vector
	public double dotProduct(Vector3f vector2)
	{
		return(
				(this.getX() * vector2.getX()) + 
				(this.getY() * vector2.getY()) +
				(this.getZ() * vector2.getZ()));
	}

	// Check for equality
	public boolean equalsThisVector(Vector3f otherVector)
	{	
		return this.equals(otherVector);
	}

	// Check for vector equality
	public static boolean vectorEquality(Vector3f vector1, Vector3f vector2)
	{
		return vector1.equals(vector2);
	}

	// Check two vectors for inequality
	public static boolean vectorNotEqual(Vector3f vector1, Vector3f vector2)
	{
		return !(vector1.equals(vector2));
	}

	/************************************
	 * Functions for debugging purposes *
	 ************************************/
	// Print the contents of the Vector to the debug display
	public void PrintVector( String debugText )
	{
		System.out.println( debugText );
		System.out.println( Double.toString( this.x ) + ", " + Double.toString( this.y ) + ", " + Double.toString( this.z ) );
	}

	// Print the contents of the Vector to the debug display
	public String TxtPrintVector( String debugText )
	{
		return (debugText + " \r\n" + Double.toString( this.x ) + ", " + Double.toString( this.y ) + ", " + Double.toString( this.z ) + " \r\n");
	}
}