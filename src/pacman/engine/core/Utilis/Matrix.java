package pacman.engine.core.Utilis;

import pacman.engine.core.Entity.Entity;

public class Matrix {

    public static Entity[][] extractSubMatrix(
            Entity[][] matrix,
            int rowStart, int rowEnd,
            int colStart, int colEnd
    )
    {
        int sizeRow = rowEnd - rowStart;
        int sizeCol = colEnd - colStart;

        Entity[][] result = new Entity[sizeRow][sizeCol];

        for(int i = rowStart; i < rowEnd; i++) {

            for(int j = colStart; j < colEnd; j++) {

                int x = i - rowStart;
                int y = j - colStart;

                result[x][y] = matrix[i][j];
            }
        }

        return result;
    }
}
