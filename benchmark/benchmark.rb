#!/usr/bin/env ruby

count = (ARGV[0] || 1000).to_i
path = File.dirname(__FILE__)
 
require 'base64'
require 'benchmark'
require 'openssl'

# configure keys
public_key_file = "#{path}/foo_cert.pem" # public key in cert file
private_key_file = "#{path}/foo.pem"     # private key file
 
$public_key = OpenSSL::X509::Certificate.new(File.read(public_key_file)).public_key
$private_key = OpenSSL::PKey::RSA.new(File.read(private_key_file))

# example msg
msg =  "THIS IS A TEST"
 
# example key for encrypt operation
aes = OpenSSL::Cipher::Cipher.new('aes-256-cbc')
aes.encrypt
key = aes.random_key

def sign(msg)
  $private_key.sign(OpenSSL::Digest.new('sha512'), msg)
end
 
def verify(msg, signature)
  $public_key.verify(OpenSSL::Digest.new('sha512'), signature, msg)
end
 
def encrypt(content)
  $public_key.public_encrypt(content)
end
 
def decrypt(encrypted_msg)
  $private_key.private_decrypt(encrypted_msg)
end
 
# signature, encrypted key for verify, decrypt tests
signature = sign(msg)
encrypted_key = encrypt(msg)
 
puts "#{RUBY_ENGINE} N = #{count}"
Benchmark.bm(17) do |x|
  x.report('sign') { count.times { sign(msg) } }
  x.report('verify') { count.times { verify(msg, signature) } }
  x.report('encrypt') { count.times { encrypt(key) } }
  x.report('decrypt') { count.times { decrypt(encrypted_key) } }
end
puts
p verify(msg, signature)
p decrypt(encrypted_key)
