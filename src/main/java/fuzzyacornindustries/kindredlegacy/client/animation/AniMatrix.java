package fuzzyacornindustries.kindredlegacy.client.animation;

public class AniMatrix
{
	public double[][] matrix;

	// Constructor
	public AniMatrix()
	{
		matrix = new double[4][4];
		this.unity();
	}

	// Normal Constructor-Takes 16 doubles
	public AniMatrix(double m00, double m01, double m02, double m03,
			double m04, double m05, double m06, double m07,
			double m08, double m09, double m10, double m11,
			double m12, double m13, double m14, double m15)
	{
		matrix = new double[4][4];

		matrix[0][0] = m00; matrix[0][1] = m01; matrix[0][2] = m02; matrix[0][3] = m03;
		matrix[1][0] = m04; matrix[1][1] = m05; matrix[1][2] = m06; matrix[1][3] = m07;
		matrix[2][0] = m08; matrix[2][1] = m09; matrix[2][2] = m10; matrix[2][3] = m11;
		matrix[3][0] = m12; matrix[3][1] = m13; matrix[3][2] = m14; matrix[3][3] = m15;
	}

	// Set matrix default
	public void unity()
	{
		// Set whole matrix to 0
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				matrix[i][j] = 0F;
			}
		}

		// Set diagonals to 1
		matrix[0][0] = matrix[1][1] = matrix[2][2] = matrix[3][3] = 1F;
	}

	public static AniMatrix multiMatrixByMatrix(AniMatrix thisMatrix, AniMatrix otherMatrix)
	{
		return new AniMatrix(
				// Set each component in the top row
				(thisMatrix.matrix[0][0] * otherMatrix.matrix[0][0]) + (thisMatrix.matrix[0][1] * otherMatrix.matrix[1][0]) +
				(thisMatrix.matrix[0][2] * otherMatrix.matrix[2][0]) + (thisMatrix.matrix[0][3] * otherMatrix.matrix[3][0]),

				(thisMatrix.matrix[0][0] * otherMatrix.matrix[0][1]) + (thisMatrix.matrix[0][1] * otherMatrix.matrix[1][1]) +
				(thisMatrix.matrix[0][2] * otherMatrix.matrix[2][1]) + (thisMatrix.matrix[0][3] * otherMatrix.matrix[3][1]),

				(thisMatrix.matrix[0][0] * otherMatrix.matrix[0][2]) + (thisMatrix.matrix[0][1] * otherMatrix.matrix[1][2]) +
				(thisMatrix.matrix[0][2] * otherMatrix.matrix[2][2]) + (thisMatrix.matrix[0][3] * otherMatrix.matrix[3][2]),

				(thisMatrix.matrix[0][0] * otherMatrix.matrix[0][3]) + (thisMatrix.matrix[0][1] * otherMatrix.matrix[1][3]) +
				(thisMatrix.matrix[0][2] * otherMatrix.matrix[2][3]) + (thisMatrix.matrix[0][3] * otherMatrix.matrix[3][3]),

				// Set each component in the second row individually
				(thisMatrix.matrix[1][0] * otherMatrix.matrix[0][0]) + (thisMatrix.matrix[1][1] * otherMatrix.matrix[1][0]) +
				(thisMatrix.matrix[1][2] * otherMatrix.matrix[2][0]) + (thisMatrix.matrix[1][3] * otherMatrix.matrix[3][0]),

				(thisMatrix.matrix[1][0] * otherMatrix.matrix[0][1]) + (thisMatrix.matrix[1][1] * otherMatrix.matrix[1][1]) +
				(thisMatrix.matrix[1][2] * otherMatrix.matrix[2][1]) + (thisMatrix.matrix[1][3] * otherMatrix.matrix[3][1]),

				(thisMatrix.matrix[1][0] * otherMatrix.matrix[0][2]) + (thisMatrix.matrix[1][1] * otherMatrix.matrix[1][2]) +
				(thisMatrix.matrix[1][2] * otherMatrix.matrix[2][2]) + (thisMatrix.matrix[1][3] * otherMatrix.matrix[3][2]),

				(thisMatrix.matrix[1][0] * otherMatrix.matrix[0][3]) + (thisMatrix.matrix[1][1] * otherMatrix.matrix[1][3]) +
				(thisMatrix.matrix[1][2] * otherMatrix.matrix[2][3]) + (thisMatrix.matrix[1][3] * otherMatrix.matrix[3][3]),

				// Set each component in the third row
				(thisMatrix.matrix[2][0] * otherMatrix.matrix[0][0]) + (thisMatrix.matrix[2][1] * otherMatrix.matrix[1][0]) +
				(thisMatrix.matrix[2][2] * otherMatrix.matrix[2][0]) + (thisMatrix.matrix[2][3] * otherMatrix.matrix[3][0]),

				(thisMatrix.matrix[2][0] * otherMatrix.matrix[0][1]) + (thisMatrix.matrix[2][1] * otherMatrix.matrix[1][1]) +
				(thisMatrix.matrix[2][2] * otherMatrix.matrix[2][1]) + (thisMatrix.matrix[2][3] * otherMatrix.matrix[3][1]),

				(thisMatrix.matrix[2][0] * otherMatrix.matrix[0][2]) + (thisMatrix.matrix[2][1] * otherMatrix.matrix[1][2]) +
				(thisMatrix.matrix[2][2] * otherMatrix.matrix[2][2]) + (thisMatrix.matrix[2][3] * otherMatrix.matrix[3][2]),

				(thisMatrix.matrix[2][0] * otherMatrix.matrix[0][3]) + (thisMatrix.matrix[2][1] * otherMatrix.matrix[1][3]) +
				(thisMatrix.matrix[2][2] * otherMatrix.matrix[2][3]) + (thisMatrix.matrix[2][3] * otherMatrix.matrix[3][3]),

				// Set each component in the fourth row
				(thisMatrix.matrix[3][0] * otherMatrix.matrix[0][0]) + (thisMatrix.matrix[3][1] * otherMatrix.matrix[1][0]) +
				(thisMatrix.matrix[3][2] * otherMatrix.matrix[2][0]) + (thisMatrix.matrix[3][3] * otherMatrix.matrix[3][0]),

				(thisMatrix.matrix[3][0] * otherMatrix.matrix[0][1]) + (thisMatrix.matrix[3][1] * otherMatrix.matrix[1][1]) +
				(thisMatrix.matrix[3][2] * otherMatrix.matrix[2][1]) + (thisMatrix.matrix[3][3] * otherMatrix.matrix[3][1]),

				(thisMatrix.matrix[3][0] * otherMatrix.matrix[0][2]) + (thisMatrix.matrix[3][1] * otherMatrix.matrix[1][2]) +
				(thisMatrix.matrix[3][2] * otherMatrix.matrix[2][2]) + (thisMatrix.matrix[3][3] * otherMatrix.matrix[3][2]),

				(thisMatrix.matrix[3][0] * otherMatrix.matrix[0][3]) + (thisMatrix.matrix[3][1] * otherMatrix.matrix[1][3]) +
				(thisMatrix.matrix[3][2] * otherMatrix.matrix[2][3]) + (thisMatrix.matrix[3][3] * otherMatrix.matrix[3][3])
				);
	}

	// Multiply a matrix by a 3 float vector, vector3f
	public static Vector3f multiMatrixByVector3f(AniMatrix thisMatrix, Vector3f vector)
	{
		return new Vector3f(multiMatrixByVector4f(thisMatrix, new Vector4f(vector)));
	}

	// Multiply a matrix by a 4 float vector, vector4f
	public static Vector4f multiMatrixByVector4f(AniMatrix thisMatrix, Vector4f vector)
	{
		return new Vector4f (
				(float)(thisMatrix.matrix[0][0] * vector.getX()) + (float)(thisMatrix.matrix[0][1] * vector.getY()) + 
				(float)(thisMatrix.matrix[0][2] * vector.getZ()) + (float)(thisMatrix.matrix[0][3] * vector.getW()),

				(float)(thisMatrix.matrix[1][0] * vector.getX()) + (float)(thisMatrix.matrix[1][1] * vector.getY()) +
				(float)(thisMatrix.matrix[1][2] * vector.getZ()) + (float)(thisMatrix.matrix[1][3] * vector.getW()),

				(float)(thisMatrix.matrix[2][0] * vector.getX()) + (float)(thisMatrix.matrix[2][1] * vector.getY()) +
				(float)(thisMatrix.matrix[2][2] * vector.getZ()) + (float)(thisMatrix.matrix[2][3] * vector.getW()),

				(float)(thisMatrix.matrix[3][0] * vector.getX()) + (float)(thisMatrix.matrix[3][1] * vector.getY()) +
				(float)(thisMatrix.matrix[3][2] * vector.getZ()) + (float)(thisMatrix.matrix[3][3] * vector.getW())
				);
	}

	// Calculate the transpose of a matrix
	public AniMatrix transpose()
	{
		return new AniMatrix(
				matrix[0][0], matrix[1][0], matrix[2][0], matrix[3][0],
				matrix[0][1], matrix[1][1], matrix[2][1], matrix[3][1],
				matrix[0][2], matrix[1][2], matrix[2][2], matrix[3][2],
				matrix[0][3], matrix[1][3], matrix[2][3], matrix[3][3]
				);
	}

	// Toggle between a left and right handed system
	public AniMatrix convertSystem()
	{
		AniMatrix temp = transpose();

		temp.matrix[2][1] *= -1.0F;
		temp.matrix[2][2] *= -1.0F;
		temp.matrix[2][3] *= -1.0F;
		temp.matrix[3][0] *= -1.0F;

		return temp;
	}

	// Rotate matrix on the X axis
	public AniMatrix rotateX(double angle)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				1.0F, 0.0F, 0.0F, 0.0F,
				0.0F, Math.cos(angle), -Math.sin(angle), 0.0F,
				0.0F, Math.sin(angle), Math.cos(angle), 0.0F,
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}

	// Rotate matrix on the Y axis
	public AniMatrix rotateY(double angle)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				Math.cos(angle), 0.0F, Math.sin(angle), 0.0F,
				0.0F, 1.0F, 0.0F, 0.0F,
				-Math.sin(angle), 0.0F, Math.cos(angle), 0.0F,
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}

	// Rotate matrix on the Z axis
	public AniMatrix rotateZ(double angle)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				Math.cos(angle), -Math.sin(angle), 0.0F, 0.0F,
				Math.sin(angle), Math.cos(angle), 0.0F, 0.0F,
				0.0F, 0.0F, 1.0F, 0.0F,
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}

	public AniMatrix copyRotation()
	{
		return new AniMatrix(
				matrix[0][0], matrix[0][1], matrix[0][2], 0.0F,
				matrix[1][0], matrix[1][1], matrix[1][2], 0.0F,
				matrix[2][0], matrix[2][1], matrix[2][2], 0.0F,
				matrix[3][0], matrix[3][1], matrix[3][2], 1.0F);
	}

	public AniMatrix copyTranslation()
	{
		return new AniMatrix(
				1.0F, 0.0F, 0.0F, matrix[0][3],
				0.0F, 1.0F, 0.0F, matrix[1][3],
				0.0F, 0.0F, 1.0F, matrix[2][3],
				0.0F, 0.0F, 0.0F, 1.0F);
	}

	// Make a matrix by a translation vector
	public AniMatrix translate(Vector3f translation)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				1.0F, 0.0F, 0.0F, translation.getX(),
				0.0F, 1.0F, 0.0F, translation.getY(),
				0.0F, 0.0F, 1.0F, translation.getZ(),
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}

	// Make a matrix by three float values
	public AniMatrix translate(float x, float y, float z)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				1.0F, 0.0F, 0.0F, x,
				0.0F, 1.0F, 0.0F, y,
				0.0F, 0.0F, 1.0F, z,
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}

	// Make a matrix a uniform scaling matrix
	public AniMatrix scale(float scale)
	{
		return multiMatrixByMatrix(this, new AniMatrix(
				scale, 0.0F, 0.0F, 0.0F,
				0.0F, scale, 0.0F, 0.0F,
				0.0F, 0.0F, scale, 0.0F,
				0.0F, 0.0F, 0.0F, 1.0F)
				);
	}
	
	/************************************
	 * Functions for debugging purposes *
	 ************************************/
	// Print the contents of the Vector to the debug display
	public void PrintMatrix( String debugText )
	{
		System.out.println( debugText );
		for( int row = 0; row < 4; row++ )
		{
			System.out.println( Double.toString( this.matrix[row][0] ) + ", " + Double.toString( this.matrix[row][1] ) + ", " + Double.toString( this.matrix[row][2] ) + ", " + Double.toString( this.matrix[row][3] ) );
		}
		System.out.println( "----------" );
	}
	
	// Print the contents of the Vector to the debug display
		public String TxtPrintMatrix( String debugText )
		{
			String returnString;
			returnString = debugText + " \r\n";
			for( int row = 0; row < 4; row++ )
			{
				returnString = returnString + Double.toString( this.matrix[row][0] ) + ", " + Double.toString( this.matrix[row][1] ) + ", " + Double.toString( this.matrix[row][2] ) + ", " + Double.toString( this.matrix[row][3]) + " \r\n";
			}
			returnString = returnString + "---------- \r\n";
			
			return returnString;
		}
}