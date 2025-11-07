INSERT INTO Categories (Name, Description) VALUES
('Makanan', 'Bahan makanan dan minuman mentah atau yang belum diproses.'),
('Elektronik', 'Handphone, camera, komputer dan perlengkapan elektronik lainnya.'),
('Fashion', 'Busana dan accessories untuk pria dan wanita.'),
('Buku', 'Segala macam buku baik hard copy maupun digital e-book.'),
('Olahraga', 'Perlengkapan sport seperti bola, pemukul kasti, samsak dan lain sebagainya.'),
('Mainan', 'Macam-macan mainan, koleksi dan barang-barang hobby lainnya.'),
('Dapur', 'Peralatan masak dan peralatan dapur lainnya.'),
('Musik', 'Alat-alat musik dan perlengkapan audio lainnya.'),
('Kesehatan', 'Obat, supplement, dan jenis-jenis vitamin lainnya.'),
('Stationary', 'Peralatan kantor, kuliah dan sekolah seperti alat tulis dan lain-lain.'),
('Pertukangan', 'Alat-alat pembangunan dan crafting.'),
('Furniture', 'Lemari, meja, kursi dan furniture untuk interior lainnya.'),
('Rumah Tangga', 'Peralatan dan perlengkapan untuk interior rumah atau apartment.');

INSERT INTO Products (Name, Description, Price, UnitsInStock, OnOrder, Discontinue, CategoryId) VALUES
('Beras Premium 5kg', 'Beras pulen kualitas super untuk kebutuhan sehari-hari', 75000, 100, 20, 0, 1),
('Laptop Pro 14"', 'Laptop performa tinggi dengan prosesor generasi terbaru', 14500000, 25, 5, 0, 2),
('Novel Fiksi Best Seller', 'Novel fiksi populer dengan cerita menarik dan penuh imajinasi', 95000, 60, 10, 0, 4),
('Handphone Seri Lama', 'Model lama yang sudah tidak diproduksi lagi', 2500000, 15, 0, 1, 2);


@@
-- Drop the procedures if they exist
IF OBJECT_ID('ExchangeStockOnOrder', 'P') IS NOT NULL
    DROP PROCEDURE ExchangeStockOnOrder;
@@
IF OBJECT_ID('GetProductsByCategory', 'P') IS NOT NULL
    DROP PROCEDURE GetProductsByCategory;
@@
IF OBJECT_ID('PricePerCategories', 'P') IS NOT NULL
    DROP PROCEDURE PricePerCategories;
@@
CREATE PROCEDURE ExchangeStockOnOrder
    @ProductId INT
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE Products SET UnitsInStock = UnitsInStock + ISNULL(OnOrder, 0), OnOrder = 0 WHERE Id = @ProductId AND Discontinue = 0;
END
@@

CREATE PROCEDURE GetProductsByCategory
    @CategoryName NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    SELECT p.Id, p.Name, p.Description, p.Price, p.UnitsInStock, p.OnOrder, p.Discontinue, p.CategoryId
    FROM Products p
        INNER JOIN Categories c ON p.CategoryId = c.Id
    WHERE c.Name = @CategoryName AND p.Discontinue = 0;
END
@@

CREATE PROCEDURE PricePerCategories
AS
BEGIN
    SET NOCOUNT ON;
    SELECT c.Id, c.Name, c.Description, AVG(p.Price) AS 'avgPrice'
    FROM Categories c
        INNER JOIN Products p ON p.CategoryId = c.Id
    GROUP BY c.Id, c.Name, c.Description
END
@@